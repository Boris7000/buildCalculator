package com.psuti.buildcalculator.service.impl;

import com.psuti.buildcalculator.service.UserCrudService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserCrudService userCrudService;
    public UserDetailsServiceImpl(UserCrudService userCrudService){
        this.userCrudService = userCrudService;
    }

    public UserDetails loadUserById(Integer id){
        return fromEntity(userCrudService.getById(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return fromEntity(userCrudService.getByUsername(username));
    }

    UserDetails fromEntity(com.psuti.buildcalculator.entities.User user){
        boolean enabled = user.isEnabled();
        return new User(
                user.getEmail(),
                user.getPassword(),
                enabled,enabled,enabled,enabled,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

}
