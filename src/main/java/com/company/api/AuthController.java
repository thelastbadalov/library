package com.company.api;

import com.company.dto.TokenResponseDto;
import com.company.request.LoginRequest;
import com.company.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

@PostMapping("/login")

    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest request){
    return ResponseEntity.ok(authenticationService.login(request));

}

}
