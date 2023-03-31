package uz.pdp.appproblemsolver.payload;

import uz.pdp.appproblemsolver.entity.enums.Permission;

import java.util.List;

public record RoleDTO(
        String name,
        List<Permission> permissions
) {
}
