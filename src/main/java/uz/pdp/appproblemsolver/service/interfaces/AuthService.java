package uz.pdp.appproblemsolver.service.interfaces;

import uz.pdp.appproblemsolver.payload.*;

public interface AuthService {

    ApiResult<?> register(RegisterDTO registerDTO);
    ApiResult<?> authenticate(AuthDTO authDTO);
    ApiResult<?> verification(String email, String token);
}
