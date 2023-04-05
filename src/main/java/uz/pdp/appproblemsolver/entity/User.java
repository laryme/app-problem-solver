package uz.pdp.appproblemsolver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'https://problem-solver.s3.eu-central-1.amazonaws.com/problem-solver/projects/problem-solver/images/avatar/default.jpeg'", nullable = false)
    private String avatarUrl;
    @ManyToOne(optional = false)
    private Role role;
    @Column(columnDefinition = "boolean DEFAULT false")
    private boolean isActive;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isNonDeleted = true;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isNonBlocked = true;

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatarUrl = "https://problem-solver.s3.eu-central-1.amazonaws.com/problem-solver/projects/problem-solver/images/avatar/default.jpeg";
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissionSet();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isNonDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNonBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
