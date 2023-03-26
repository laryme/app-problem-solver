package uz.pdp.appproblemsolver.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.appproblemsolver.dto.AuthDTO;
import uz.pdp.appproblemsolver.dto.RegisterDTO;
import uz.pdp.appproblemsolver.utils.Constants;

@RequestMapping(AuthController.BASE_PATH)
public interface AuthController {
    String BASE_PATH = Constants.BASE_PATH+"/auth";
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterDTO authDTO, BindingResult result);
    @PostMapping("/authenticate")
    ResponseEntity<?> authenticate(@RequestBody AuthDTO authDTO, BindingResult result);
}
