package org.pizzaproject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GeneralUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private final static GrantedAuthority USER_AUTHORITY = (GrantedAuthority) () -> "USER";
    private final static GrantedAuthority ADMIN_AUTHORITY = (GrantedAuthority) () -> "ADMIN";

    public GeneralUserDetails() {
    }

    public GeneralUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public GeneralUserDetails(User user){
        this.username = user.getName();
        this.password = user.getPassword();
        Set<GrantedAuthority> authoritiesSet = new HashSet<>();

        if(user.getUserAuthority().equals("USER")){
            authoritiesSet.add(USER_AUTHORITY);
        }

        if(user.getUserAuthority().equals("ADMIN")){
            authoritiesSet.add(USER_AUTHORITY);
            authoritiesSet.add(ADMIN_AUTHORITY);
        }
        this.authorities = authoritiesSet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
