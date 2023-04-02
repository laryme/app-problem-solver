package uz.pdp.appproblemsolver.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    CATEGORY_LIST,
    CATEGORY_ONE,
    CATEGORY_CREATE,
    PROBLEM_CREATE,
    ADMIN_PROBLEM_LIST;

    @Override
    public String getAuthority() {
        return name();
    }
}
