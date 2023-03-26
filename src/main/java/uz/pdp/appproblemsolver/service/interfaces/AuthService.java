package uz.pdp.appproblemsolver.service.interfaces;

import uz.pdp.appproblemsolver.dto.AuthDTO;
import uz.pdp.appproblemsolver.dto.RegisterDTO;
import uz.pdp.appproblemsolver.dto.ResultMessage;

public interface AuthService {

    ResultMessage register(RegisterDTO registerDTO);
    ResultMessage authenticate(AuthDTO authDTO);
}
