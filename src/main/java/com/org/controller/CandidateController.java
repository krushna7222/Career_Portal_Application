package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		 
		 Candidate candidate =  candidateService.register(data);
		 	
		 	ApiResponse<Candidate> response = new ApiResponse<Candidate>(200, candidate, "Candidate Registered Succesfully...");
	        
		 	return ResponseEntity.status(201).body(response);
	    }
	 
<<<<<<< HEAD
	 
	 @GetMapping("/profile/{id}")
	 public ResponseEntity<Candidate> profile(@PathVariable Integer id){
		 return new ResponseEntity<>(candidateService.viewProfile(id), HttpStatus.OK);
	 }
	
=======
>>>>>>> 9a98d3943aa4c5495d7a802436b1067199b30f18
}
