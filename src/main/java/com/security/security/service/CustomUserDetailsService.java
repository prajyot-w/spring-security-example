package com.security.security.service;

import com.security.security.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Implement UserDetailsService which implements a single method loadUserByUsername.
     * Which is injected into auth.userDetailsService. */

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("Username not found.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAutorities(user));
    }

    private List<GrantedAuthority> grantedAutorities(User user){
        /**
         * Generates list of GrantedAuthority generated from roles.
         * */
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        });
        return grantedAuthorities;
    }

}
