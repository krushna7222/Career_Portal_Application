package com.org.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.entity.Candidate;
import com.org.repository.CandidateRepository;
import com.org.service.AdminService;
import com.org.utils.JwtUtil;

@Service
public class AdminServiceImpl implements AdminService {

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private CandidateRepository candidateRepo;


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

	public List<Candidate> getAllCandidate() {

		List<Candidate> clist = candidateRepo.findAll();
		return clist;

	}
}
