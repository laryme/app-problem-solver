package uz.pdp.appproblemsolver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appproblemsolver.dto.AuthDTO;
import uz.pdp.appproblemsolver.dto.RegisterDTO;
import uz.pdp.appproblemsolver.dto.ResultMessage;
import uz.pdp.appproblemsolver.entity.User;
import uz.pdp.appproblemsolver.entity.enums.RoleType;
import uz.pdp.appproblemsolver.repository.RoleRepository;
import uz.pdp.appproblemsolver.repository.UserRepository;
import uz.pdp.appproblemsolver.security.JwtService;
import uz.pdp.appproblemsolver.service.interfaces.AuthService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResultMessage register(RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.username()))
            return new ResultMessage(false, "Username is already registered");
        else if (userRepository.existsByEmail(registerDTO.email()))
            return new ResultMessage(false, "Email is already registered");

        User regUser = new User(
                registerDTO.username(),
                passwordEncoder.encode(registerDTO.password()),
                registerDTO.email(),
                roleRepository.findByRoleType(RoleType.USER).orElseThrow()
        );

        userRepository.save(regUser);
        //todo send email activation link to user
        return new ResultMessage(true, jwtService.generateToken(regUser));
    }
    @Override
    public ResultMessage authenticate(AuthDTO authDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.username(),
                        authDTO.password()
                )
        );

        return new ResultMessage(
                true,
                jwtService.generateToken((User) authenticate.getPrincipal())
        );
    }
}
