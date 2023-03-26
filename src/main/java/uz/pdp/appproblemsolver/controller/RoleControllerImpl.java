package uz.pdp.appproblemsolver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appproblemsolver.controller.interfaces.RoleController;
import uz.pdp.appproblemsolver.dto.RoleDTO;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.service.RoleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;
    @Override
    public ResponseEntity<List<Role>> getAllRole() {
        return null;
    }

    @Override
    public ResponseEntity<Role> getRoleById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createNewRole(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteRoleById(Integer id) {
        return null;
    }
}
