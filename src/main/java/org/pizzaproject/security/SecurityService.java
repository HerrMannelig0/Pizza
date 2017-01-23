package org.pizzaproject.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SecurityService implements UserDetailsService {

    private String userServiceUrl = "http://localhost:8080/UsersSecurityService-1.0.0/user";
    private RestTemplate template = new RestTemplate();

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = template.getForObject(userServiceUrl + "?name=" + name, User.class);

        UserDetails userDetails = new GeneralUserDetails(user);
        return userDetails;
    }
}
