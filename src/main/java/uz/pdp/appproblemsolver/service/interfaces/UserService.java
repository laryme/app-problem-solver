package uz.pdp.appproblemsolver.service.interfaces;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.UserDTO;

import java.util.UUID;

public interface UserService {
    ApiResult<?> getAllUsers(PageRequest pageRequest);

    ApiResult<?> getUserById(UUID id);

    ApiResult<?> editUser(UUID id, UserDTO userDTO);

    ApiResult<?> changeDeletedFieldStatus(UUID id);

    ApiResult<?> deleteUserById(UUID id);

    ApiResult<?> changeBlockStatus(UUID id);

    ApiResult<?> promoteUserToNewRole(UUID id, Integer roleId);

    ApiResult<?> changeUserAvatar(UUID id, MultipartFile file);
}
