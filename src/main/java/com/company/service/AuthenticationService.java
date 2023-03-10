package com.company.service;

import com.company.dto.TokenResponseDto;
import com.company.exception.UserNotFoundException;
import com.company.request.LoginRequest;
import com.company.utils.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenGenerator tokenGenerator;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    public TokenResponseDto login(LoginRequest request){
    try {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        return TokenResponseDto.builder()
                .accessToken(tokenGenerator.generateToken(authenticate))
                .userDto(userService.getUser(request.getUsername()))
                .build();
    }catch (Exception ex){
      throw new UserNotFoundException("can not find user");
    }

    }
}
