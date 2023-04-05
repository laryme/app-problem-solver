package uz.pdp.appproblemsolver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appproblemsolver.controller.interfaces.UserController;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.UserDTO;
import uz.pdp.appproblemsolver.service.interfaces.UserService;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    @Override
    public ApiResult<?> getAllUsers(Integer page, int size, String[] sort) {
        return userService.getAllUsers(
                PageRequest.of(
                        page,
                        size,
                        Objects.equals(sort[1], "desc") ?
                                Sort.by(sort[0])
                                        .descending() :
                                Sort.by(sort[0])
                                        .ascending()));
    }

    @Override
    public ApiResult<?> getUserById(UUID id) {
        return userService.getUserById(id);
    }

    @Override
    public ApiResult<?> editUser(UUID id, UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    @Override
    public ApiResult<?> deleteUser(UUID id) {
        return userService.deleteUserById(id);
    }

    @Override
    public ApiResult<?> blockUser(UUID id) {
        return userService.changeBlockStatus(id);
    }

    @Override
    public ApiResult<?> promoteUserToNewRole(UUID id, Integer roleId) {
        return userService.promoteUserToNewRole(id, roleId);
    }

    @Override
    public ApiResult<?> changeUserAvatar(UUID id, MultipartFile file) {
        return userService.changeUserAvatar(id, file);
    }
}
