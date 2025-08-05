package com.org.controller;

import java.util.List;
import java.util.Map;
import com.org.utils.TokenBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.entity.Candidate;
import com.org.serviceImpl.AdminServiceImpl;
import com.org.utils.ApiResponse;

@RestController
@RequestMapping("/career-portal/admin")
public class AdminController {

    private final TokenBlacklist tokenBlacklist;


	@Autowired
	private AdminServiceImpl adminServiceImpl;

    AdminController(TokenBlacklist tokenBlacklist) {
        this.tokenBlacklist = tokenBlacklist;
    }

	@PostMapping("/refresh-token")
	public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
		String refreshToken = request.get("refreshToken");
		String newAccess = adminServiceImpl.refreshAccessToken(refreshToken);
		return ResponseEntity.ok(Map.of("accessToken", newAccess));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody Map<String, String> request) {

		try {
			String email = request.get("email");
			String password = request.get("password");

			Map<String, String> tokens = adminServiceImpl.login(email, password);

			ApiResponse<Map<String, String>> response = new ApiResponse<>(200, tokens, "Admin logged in successfully.");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			ApiResponse<Map<String, String>> errorResponse = new ApiResponse<>(401, null, e.getMessage());
			return ResponseEntity.status(401).body(errorResponse);
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String authHeader) {
	    String token = authHeader.replace("Bearer ", "");
	    tokenBlacklist.blacklist(token);
	    ApiResponse<Void> response = new ApiResponse<>(200, null, "Admin logged out successfully.");
	    return ResponseEntity.ok(response);
	}


	@GetMapping("/getAllCandidate")
	public ResponseEntity<ApiResponse<List<Candidate>>> getAllCandidate() {

		List<Candidate> candidates = adminServiceImpl.getAllCandidate();

		ApiResponse<List<Candidate>> response = new ApiResponse<>(200, candidates, "All Candidate Feth Succesfully...");

		return ResponseEntity.status(201).body(response);

	}

}
