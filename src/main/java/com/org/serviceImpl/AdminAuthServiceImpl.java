package com.org.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.dto.AdminLoginRequest;
import com.org.dto.TokenResponse;
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

    public TokenResponse login(AdminLoginRequest req) {
        if (!adminEmail.equals(req.getEmail()))
            throw new RuntimeException("Invalid Email");

        if (!passwordEncoder.matches(req.getPassword(), adminPassword))
            throw new RuntimeException("Invalid Password");

        String access = jwtUtil.generateAccessToken(req.getEmail());
        String refresh = jwtUtil.generateRefreshToken(req.getEmail());

        return new TokenResponse(access, refresh);
    }

    public String refreshAccessToken(String refreshToken) {
        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new RuntimeException("Invalid or expired refresh token");
        }
        String email = jwtUtil.getEmailFromToken(refreshToken);
        return jwtUtil.generateAccessToken(email);
    }
}
