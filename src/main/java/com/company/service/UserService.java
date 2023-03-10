package com.company.service;

import com.company.dto.UserDto;
import com.company.exception.UserNotFoundException;
import com.company.model.User;
import com.company.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
private final BCryptPasswordEncoder passwordEncoder;


public UserDto createUser(User user){
   user.setPassword(passwordEncoder.encode(user.getPassword()));
   var savedUser=userRepository.save(user);
   return UserDto.builder()
           .userRole(user.getUserRole())
           .email(user.getEmail())
           .username(user.getUsername())
           .build();

}
    public UserDto getUser(String username){
        var savedUser=findUserByUsername( username);
        return UserDto.builder()
                .userRole(savedUser.getUserRole())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();

    }
public User findUserByUsername(String username){
return userRepository.findUserByUsername(username).orElseThrow(()->
        new UserNotFoundException("can not find user with given"+ username));
}

}
