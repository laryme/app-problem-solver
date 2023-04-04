package uz.pdp.appproblemsolver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appproblemsolver.controller.interfaces.RoleController;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.RoleDTO;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.service.interfaces.RoleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;
    @Override
    public ApiResult<List<Role>> getAllRole() {
        return roleService.getAllRoles();
    }

    @Override
    public ApiResult<Role> getRoleById(Integer id) {
        return roleService.getRoleById(id);
    }

    @Override
    public ApiResult<?> createNewRole(RoleDTO roleDTO) {
        return roleService.createNewRole(roleDTO);
    }

    @Override
    public ApiResult<?> updateRole(Integer id, RoleDTO roleDTO) {
        return roleService.updateRole(id, roleDTO);
    }

    @Override
    public ApiResult<?> deleteRoleById(Integer id) {
        return roleService.deleteRoleById(id);
    }
}
