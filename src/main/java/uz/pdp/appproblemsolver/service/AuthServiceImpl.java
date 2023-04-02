package uz.pdp.appproblemsolver.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appproblemsolver.entity.User;
import uz.pdp.appproblemsolver.entity.enums.RoleType;
import uz.pdp.appproblemsolver.exception.EmailNotVerifiedException;
import uz.pdp.appproblemsolver.exception.EmailSendingException;
import uz.pdp.appproblemsolver.exception.TokenExpiredOrInvalid;
import uz.pdp.appproblemsolver.exception.UsernameOrEmailAlreadyExists;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.AuthDTO;
import uz.pdp.appproblemsolver.payload.RegisterDTO;
import uz.pdp.appproblemsolver.payload.TokenDTO;
import uz.pdp.appproblemsolver.repository.RoleRepository;
import uz.pdp.appproblemsolver.repository.UserRepository;
import uz.pdp.appproblemsolver.security.JwtService;
import uz.pdp.appproblemsolver.service.interfaces.AuthService;
import uz.pdp.appproblemsolver.utils.Constants;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSenderService emailSenderService;

    @Override
    public ApiResult<?> register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.username()))
            throw new UsernameOrEmailAlreadyExists("Username is already registered");
        else if (userRepository.existsByEmail(registerDTO.email()))
            throw new UsernameOrEmailAlreadyExists("Email is already registered");

        User regUser = new User(
                registerDTO.username(),
                passwordEncoder.encode(registerDTO.password()),
                registerDTO.email(),
                roleRepository.findByRoleType(RoleType.USER).orElseThrow()
        );

        userRepository.save(regUser);
        //todo send email activation link to user
        CompletableFuture.runAsync(() -> {
            try {
                emailSenderService.sendEmail(regUser.getEmail(),
                        jwtService.generateVerificationToken(regUser));
            } catch (MessagingException e) {
                throw new EmailSendingException("Some error happened. Please try again later");
            }
        });

        return ApiResult
                .successResponse(
                        TokenDTO.builder()
                                .accessToken(jwtService.generateAccessToken(regUser))
                                .refreshToken(jwtService.generateRefreshToken(regUser))
                                .build()
                );
    }

    @Override
    public ApiResult<?> authenticate(AuthDTO authDTO) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.username(),
                        authDTO.password()));


        User principalUser = (User) authenticate.getPrincipal();
        if(!principalUser.isEnabled()){
            CompletableFuture.runAsync(() -> {
                try {
                    emailSenderService.sendEmail(principalUser.getEmail(),
                            jwtService.generateVerificationToken(principalUser));

                    throw new EmailNotVerifiedException();
                } catch (MessagingException e) {
                    throw new EmailSendingException("Some error happened. Please try again later");
                }
            });
        }

        return ApiResult
                .successResponse(
                        new TokenDTO(
                                jwtService.generateAccessToken(principalUser),
                                jwtService.generateRefreshToken(principalUser),
                                Constants.AUTH_TYPE_BEARER));
    }

    @Override
    public ApiResult<?> verification(String email, String token) {
        try {
            if (jwtService.isVerificationTokenValid(email, token)) {
                User user = userRepository.findByEmail(email).orElseThrow(
                        () -> new TokenExpiredOrInvalid("expired token"));

                user.setActive(true);
                userRepository.save(user);
            }
        }catch (Exception e){
            throw new TokenExpiredOrInvalid("expired token");
        }
        return ApiResult
                .successResponse("Email successfully verified");
    }
}
