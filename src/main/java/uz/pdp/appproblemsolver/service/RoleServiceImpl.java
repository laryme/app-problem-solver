package uz.pdp.appproblemsolver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.entity.enums.Permission;
import uz.pdp.appproblemsolver.entity.enums.RoleType;
import uz.pdp.appproblemsolver.exception.DataNotFoundException;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.RoleDTO;
import uz.pdp.appproblemsolver.repository.RoleRepository;
import uz.pdp.appproblemsolver.service.interfaces.RoleService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ApiResult<List<Role>> getAllRoles() {
        return ApiResult
                .successResponse(roleRepository.findAll());
    }

    @Override
    public ApiResult<Role> getRoleById(Integer id) {
        return ApiResult
                .successResponse(roleRepository.findById(id)
                        .orElseThrow(() -> new DataNotFoundException("Role not found with given id")));
    }

    @Override
    public ApiResult<?> createNewRole(RoleDTO roleDTO) {
        roleRepository.save(roleMapper(roleDTO));
        return ApiResult.successResponse("Role successfully created");
    }

    @Override
    public ApiResult<?> deleteRoleById(Integer id) {
        roleRepository.deleteById(id);

        return ApiResult
                .successResponse("Role successfully deleted");
    }

    @Override
    public ApiResult<?> updateRole(Integer id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Role not found with given id"));

        role.setName(roleDTO.name());
        role.setPermissionSet(permissionMapper(roleDTO.permissions()));

        roleRepository.save(role);
        return ApiResult
                .successResponse("Role successfully updated");
    }

    private Role roleMapper(RoleDTO roleDTO) {
        return Role.builder()
                .name(roleDTO.name())
                .roleType(RoleType.OTHER)
                .permissionSet(permissionMapper(roleDTO.permissions()))
                .build();
    }

    private Set<Permission> permissionMapper(List<Integer> permissions) {
        return Arrays.stream(Permission.values())
                .filter(p -> permissions.contains(p.ordinal()))
                .collect(Collectors.toSet());
    }
}
