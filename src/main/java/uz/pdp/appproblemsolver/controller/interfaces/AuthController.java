package uz.pdp.appproblemsolver.controller.interfaces;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.AuthDTO;
import uz.pdp.appproblemsolver.payload.RegisterDTO;
import uz.pdp.appproblemsolver.utils.Constants;

@RequestMapping(AuthController.BASE_PATH)
public interface AuthController {
    String BASE_PATH = Constants.BASE_PATH+"/auth";
    @PostMapping("/register")
    ApiResult<?> register(@RequestBody RegisterDTO authDTO);
    @PostMapping("/authenticate")
    ApiResult<?> authenticate(@RequestBody AuthDTO authDTO);

    @GetMapping("/verify")
    ApiResult<?> verification(@RequestParam(name = "s") String email, @RequestParam String token);
}
