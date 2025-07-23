package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.entity.Candidate;
import com.org.serviceImpl.CandidateServiceImpl;
import com.org.utils.ApiResponse;

@RestController
@RequestMapping("/career-portal/candidate")
public class CandidateController {

	@Autowired
	private CandidateServiceImpl candidateService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<Candidate>> register(@RequestBody Candidate data) {

		Candidate candidate = candidateService.register(data);

		ApiResponse<Candidate> response = new ApiResponse<Candidate>(200, candidate,"Candidate Registered Succesfully...");

		return ResponseEntity.status(201).body(response);
	}

	@GetMapping("/profile/{id}")
	public ResponseEntity<ApiResponse<Candidate>> profile(@PathVariable Integer id) {

		Candidate candidate = candidateService.viewProfile(id);

		ApiResponse<Candidate> response = new ApiResponse<Candidate>(200, candidate,"Candidate Profile Fetched Succesfully...");

		return  ResponseEntity.status(201).body(response);
	}
}
