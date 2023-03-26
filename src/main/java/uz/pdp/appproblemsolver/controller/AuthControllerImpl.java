package uz.pdp.appproblemsolver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appproblemsolver.controller.interfaces.AuthController;
import uz.pdp.appproblemsolver.dto.AuthDTO;
import uz.pdp.appproblemsolver.dto.AuthResponse;
import uz.pdp.appproblemsolver.dto.RegisterDTO;
import uz.pdp.appproblemsolver.dto.ResultMessage;
import uz.pdp.appproblemsolver.service.interfaces.AuthService;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;
    @Override
    public ResponseEntity<?> register(@Valid RegisterDTO registerDTO, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(new ResultMessage(
                            false,
                            result.getFieldErrors()
                                    .stream()
                                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                    .collect(Collectors.toList())));
        }
        ResultMessage resultMessage = authService.register(registerDTO);

        if(resultMessage.isStatus())
            return ResponseEntity
                    .ok()
                    .body(new AuthResponse(resultMessage.getMessage()
                            .get(0)));
        else
            return ResponseEntity
                    .badRequest()
                    .body(resultMessage);

    }

    @Override
    public ResponseEntity<?> authenticate(@Valid AuthDTO authDTO, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(new ResultMessage(
                            false,
                            result.getFieldErrors()
                                    .stream()
                                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                    .collect(Collectors.toList())));
        }

        return ResponseEntity
                .ok()
                .body(new AuthResponse(
                        authService.authenticate(authDTO)
                                .getMessage()
                                .get(0)));
    }
}
