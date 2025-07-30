package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.entity.Candidate;
import com.org.serviceImpl.CandidateServiceImpl;
import com.org.utils.ApiResponse;
import com.org.utils.JwtUtil;

@RestController
@RequestMapping("/career-portal/candidate")
public class CandidateController {

    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

	@Autowired
	private CandidateServiceImpl candidateService;

    CandidateController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Candidate>> register(@RequestBody Candidate data) {

        // Encode password before saving
        data.setPassword(passwordEncoder.encode(data.getPassword()));

        // Save the candidate
        Candidate candidate = candidateService.register(data);

        // Wrap in ApiResponse
        ApiResponse<Candidate> response = new ApiResponse<>(201, candidate, "Candidate Registered Successfully...");

        return ResponseEntity.status(201).body(response);
    }

	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Candidate>> login(@RequestBody Candidate loginRequest) {

	    // 1. Get user by email
	    Candidate candidate = candidateService.findByEmail(loginRequest.getEmail());

	    // 2. Check if user exists and password matches
	    if (!passwordEncoder.matches(loginRequest.getPassword(), candidate.getPassword())) {
	        ApiResponse<Candidate> errorResponse = new ApiResponse<>(401, null, "Invalid email or password");
	        return ResponseEntity.status(401).body(errorResponse);
	    }

	    // 3. Generate JWT Token
	    String token = null;
		try {
			token = jwtUtil.generateAccessToken(candidate.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // 4. Attach token to candidate object (if your entity has no token field, skip this and send separately)
	    // Optional: You can wrap Candidate & token in a map if you don't want to modify entity
	    candidate.setToken(token); // ➤ only if your Candidate class has a `token` field

	    // 5. Send response
	    ApiResponse<Candidate> response = new ApiResponse<>(200, candidate, "Login Successful");
	    return ResponseEntity.ok(response);
	}


	@GetMapping("/profile/{id}")
	public ResponseEntity<ApiResponse<Candidate>> profile(@PathVariable Integer id) {

		Candidate candidate = candidateService.viewProfile(id);

		ApiResponse<Candidate> response = new ApiResponse<Candidate>(200, candidate,"Candidate Profile Fetched Succesfully...");

		return  ResponseEntity.status(201).body(response);
	}
}
