package com.company.service;

import com.company.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=userService.findUserByUsername(username);
        var roles= Stream.of(user.getUserRole()).map( role -> new
                SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
    }
}
