package uz.pdp.appproblemsolver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.entity.User;
import uz.pdp.appproblemsolver.exception.DataNotFoundException;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.UserDTO;
import uz.pdp.appproblemsolver.repository.RoleRepository;
import uz.pdp.appproblemsolver.repository.UserRepository;
import uz.pdp.appproblemsolver.service.interfaces.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final S3StorageService s3StorageService;

    @Value("${aws.s3.bucket.name}")
    private String s3BucketName;

    @Value("${aws.s3.endpointUrl}")
    private String endpointUrl;
    @Override
    public ApiResult<Page<User>> getAllUsers(PageRequest pageRequest) {
        return ApiResult
                .successResponse(userRepository.findAll(pageRequest));
    }

    @Override
    public ApiResult<?> getUserById(UUID id) {
        return ApiResult
                .successResponse(
                        userRepository.findById(id)
                                .orElseThrow(() -> new DataNotFoundException("User not found with given id")));
    }

    @Override
    public ApiResult<?> editUser(UUID id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with given id"));

        userRepository.save(userMapper(user, userDTO));
        return ApiResult
                .successResponse("User successfully updated");
    }

    @Override
    public ApiResult<?> changeDeletedFieldStatus(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with given id"));

        boolean nonDeleted = user.isNonDeleted();

        user.setNonDeleted(!user.isNonDeleted());
        userRepository.save(user);

        if(nonDeleted){
            return ApiResult
                    .successResponse("User successfully deleted");
        }

        return ApiResult
                .successResponse("User successfully restored");
    }

    //for delete
    @Override
    public ApiResult<?> deleteUserById(UUID id) {
        userRepository.deleteById(id);
        return ApiResult
                .successResponse("User successfully deleted");
    }

    @Override
    public ApiResult<?> changeBlockStatus(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with given id"));

        boolean flag = user.isNonBlocked();

        user.setNonBlocked(!user.isNonBlocked());
        userRepository.save(user);

        if(flag){
            return ApiResult
                    .successResponse("User successfully blocked");
        }
        return ApiResult
                .successResponse("User successfully unblocked");
    }

    @Override
    public ApiResult<?> promoteUserToNewRole(UUID id, Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new DataNotFoundException("Role not found with given id"));

        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with given id"));

        user.setRole(role);

        userRepository.save(user);

        return ApiResult
                .successResponse("User successfully promoted");
    }

    @Override
    public ApiResult<?> changeUserAvatar(UUID id, MultipartFile file) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with given id"));

        String photoUrl = "https://" + s3BucketName + endpointUrl+id;
        s3StorageService.save(id.toString(), file);

        user.setAvatarUrl(photoUrl);

        userRepository.save(user);
        return ApiResult
                .successResponse("User profile photo successfully changed");
    }

    private User userMapper(User user, UserDTO userDTO) {
        user.setFullName(userDTO.fullName());
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setEmail(user.getEmail());
        return user;
    }
}
