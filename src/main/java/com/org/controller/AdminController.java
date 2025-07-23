package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.entity.Candidate;
import com.org.serviceImpl.AdminServiceImpl;
import com.org.serviceImpl.CandidateServiceImpl;
import com.org.utils.ApiResponse;

@RestController
@RequestMapping("/career-portal/admin")
public class AdminController {
	
	@Autowired
    private AdminServiceImpl adminService;

	
	 @GetMapping("/getAllCandidate")
	 public ResponseEntity<ApiResponse<List<Candidate>>> getAllCandidate(){
		 
		 List<Candidate> candidates =  adminService.getAllCandidate();
		 
		 ApiResponse<List<Candidate>> response = new ApiResponse<>(200, candidates, "All Candidate Feth Succesfully...");

		return ResponseEntity.status(201).body(response);
		 
	 }

}
