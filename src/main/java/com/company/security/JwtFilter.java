package com.company.security;

import com.company.service.UserDetailServiceImpl;
import com.company.utils.TokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final TokenGenerator generator;
        private final UserDetailServiceImpl userDetailService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

String token=getToken(request);
String username;
try {


    if (!token.isBlank()) {
        username =generator.verifyJwt(token).getSubject();
        UserDetails userDetails=userDetailService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request,response);
}catch (Exception ex){
    response.setContentType("application/json");
    Map<String,String> errors=new HashMap<>();

    errors.put("error",ex.getMessage());
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
response.getWriter().write(objectMapper.writeValueAsString(errors));
}

    }


    private String getToken(HttpServletRequest request){
        String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header==null || !header.startsWith("Bearer ")){
            return "";
        }
    return header.substring(7);
    }
}
