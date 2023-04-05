package uz.pdp.appproblemsolver.controller.interfaces;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.UserDTO;
import uz.pdp.appproblemsolver.utils.Constants;

import java.util.UUID;

@RequestMapping(UserController.BASE_PATH)
public interface UserController {
    String BASE_PATH = Constants.BASE_PATH + "/user";

    @GetMapping("/all")
    ApiResult<?> getAllUsers(
            @RequestParam(required = false,defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id,asc") String[] sort);

    @GetMapping("/{id}")
    ApiResult<?> getUserById(@PathVariable UUID id);

    @PutMapping("/{id}")
    ApiResult<?> editUser(@PathVariable UUID id, @RequestBody UserDTO userDTO);

    @DeleteMapping("/{id}")
    ApiResult<?> deleteUser(@PathVariable UUID id);

    @GetMapping("/user-block/{id}")
    ApiResult<?> blockUser(@PathVariable UUID id);

    @PutMapping("/{id}/promote")
    ApiResult<?> promoteUserToNewRole(@PathVariable UUID id, @RequestParam Integer roleId);

    @PostMapping("/{id}/upload-avatar")
    ApiResult<?> changeUserAvatar(@PathVariable UUID id, @RequestParam("file") MultipartFile file);
}
