package com.org.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.AdminLoginRequest;
import com.org.dto.TokenResponse;
import com.org.entity.Candidate;
import com.org.serviceImpl.AdminAuthServiceImpl;
import com.org.serviceImpl.AdminServiceImpl;
import com.org.serviceImpl.CandidateServiceImpl;
import com.org.utils.ApiResponse;

@RestController
@RequestMapping("/career-portal/admin")
public class AdminController {
	
	@Autowired
    private AdminServiceImpl adminService;
	
	@Autowired
    private AdminAuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AdminLoginRequest request) {
        TokenResponse tokens = authService.login(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String newAccess = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(Map.of("accessToken", newAccess));
    }

	
	 @GetMapping("/getAllCandidate")
	 public ResponseEntity<ApiResponse<List<Candidate>>> getAllCandidate(){
		 
		 List<Candidate> candidates =  adminService.getAllCandidate();
		 
		 ApiResponse<List<Candidate>> response = new ApiResponse<>(200, candidates, "All Candidate Feth Succesfully...");

		return ResponseEntity.status(201).body(response);
		 
	 }

}
