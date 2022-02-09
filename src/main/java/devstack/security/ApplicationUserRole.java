package devstack.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    STUDENT(new HashSet<>()),
    ADMIN(new HashSet<>() {{
        add(ApplicationUserPermission.STUDENT_READ);
        add(ApplicationUserPermission.STUDENT_WRITE);
        add(ApplicationUserPermission.COURSE_READ);
        add(ApplicationUserPermission.COURSE_WRITE);
    }}),
    ADMINTRAINEE(new HashSet<>() {{
        add(ApplicationUserPermission.STUDENT_READ);
        add(ApplicationUserPermission.COURSE_READ);
    }});

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
