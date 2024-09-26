package ru.store.store_rest.model;

import org.springframework.security.core.GrantedAuthority;
import ru.logging.model.ObjectToStringForLog;

public class RoleDto extends ObjectToStringForLog implements GrantedAuthority {
    private String name;

    public RoleDto() {
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}