package uz.pdp.appproblemsolver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appproblemsolver.controller.interfaces.AuthController;
import uz.pdp.appproblemsolver.payload.*;
import uz.pdp.appproblemsolver.service.interfaces.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;
    @Override
    public ApiResult<?> register(@Valid RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }

    @Override
    public ApiResult<?> authenticate(@Valid AuthDTO authDTO) {
        return authService.authenticate(authDTO);
    }

    @Override
    public ApiResult<?> verification(String email, String token) {
        System.out.println(email+" "+token);
        return authService.verification(email, token);
    }
}
