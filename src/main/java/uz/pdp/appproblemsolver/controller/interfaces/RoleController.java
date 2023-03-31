package uz.pdp.appproblemsolver.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproblemsolver.payload.RoleDTO;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.utils.Constants;

import java.util.List;

@RequestMapping(RoleController.BASE_PATH)
public interface RoleController {
    String BASE_PATH = Constants.BASE_PATH + "/role";

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_LIST')")
    ResponseEntity<List<Role>> getAllRole();

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ONE')")
    ResponseEntity<Role> getRoleById(@PathVariable Integer id);

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    ResponseEntity<?> createNewRole(@RequestBody RoleDTO roleDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    ResponseEntity<?> deleteRoleById(@PathVariable Integer id);

}
