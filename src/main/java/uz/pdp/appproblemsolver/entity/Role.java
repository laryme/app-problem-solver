package uz.pdp.appproblemsolver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appproblemsolver.entity.enums.Permission;
import uz.pdp.appproblemsolver.entity.enums.RoleType;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;
    @ElementCollection
    @CollectionTable(name = "role_permission",
            joinColumns = {
                @JoinColumn(name = "role_id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"role_id", "permission"})})
    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissionSet;

    public Role(String name, RoleType roleType, Set<Permission> permission) {
        this.name = name;
        this.roleType = roleType;
        this.permissionSet = permission;
    }
}
