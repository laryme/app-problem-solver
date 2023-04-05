package uz.pdp.appproblemsolver.controller.interfaces;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.RoleDTO;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.utils.Constants;

import java.util.List;
import java.util.Map;

@RequestMapping(RoleController.BASE_PATH)
public interface RoleController {
    String BASE_PATH = Constants.BASE_PATH + "/role";

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_LIST')")
    ApiResult<List<Role>> getAllRole();

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ONE')")
    ApiResult<Role> getRoleById(@PathVariable Integer id);

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    ApiResult<?> createNewRole(@RequestBody RoleDTO roleDTO);

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    ApiResult<?> updateRole(@PathVariable Integer id, @RequestBody RoleDTO roleDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    ApiResult<?> deleteRoleById(@PathVariable Integer id);

    @GetMapping("/permission/all")
    ApiResult<Map<String, Integer>> getAllPermissions();

}
