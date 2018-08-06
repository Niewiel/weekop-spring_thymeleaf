package pl.niewiel.weekopspring_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RegistrationService implements UserDetailsService, Serializable {

    final
    UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("") || username.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s is invalid!", username));
        }

        return null;
    }
}
