package uz.pdp.appproblemsolver.service.interfaces;

import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.RoleDTO;

import java.util.List;

public interface RoleService {
    ApiResult<List<Role>> getAllRoles();

    ApiResult<Role> getRoleById(Integer id);

    ApiResult<?> createNewRole(RoleDTO roleDTO);

    ApiResult<?> deleteRoleById(Integer id);

    ApiResult<?> updateRole(Integer id, RoleDTO roleDTO);
}
