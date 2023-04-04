package uz.pdp.appproblemsolver.payload;

import jakarta.validation.constraints.NotBlank;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.entity.enums.Permission;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record RoleDTO(
        @NotBlank(message = "Role name is required")
        String name,

        List<Integer> permissions
) {
        public RoleDTO(Role role) {
                this(role.getName(), role.getPermissionSet().stream()
                        .map(Enum::ordinal)
                        .collect(Collectors.toList()));
        }
}
