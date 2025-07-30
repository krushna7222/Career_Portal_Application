package com.org.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.org.utils.JwtUtil;

@Service
public class AdminAuthServiceImpl {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword; 

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, String> login(String email, String password) {
        if (!adminEmail.equals(email)) {
            throw new RuntimeException("Invalid email.");
        }

        if (!passwordEncoder.matches(password, adminPassword)) {
            throw new RuntimeException("Invalid password.");
        }

        String access = jwtUtil.generateAccessToken(email);
        String refresh = jwtUtil.generateRefreshToken(email);

        Map<String, String> response = new HashMap();
        response.put("email", email);
        response.put("accessToken", access);
        response.put("refreshToken", refresh);

        return response;
    }

    public String refreshAccessToken(String refreshToken) {
        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new RuntimeException("Invalid or expired refresh token");
        }
        String email = jwtUtil.getEmailFromToken(refreshToken);
        return jwtUtil.generateAccessToken(email);
    }
}
