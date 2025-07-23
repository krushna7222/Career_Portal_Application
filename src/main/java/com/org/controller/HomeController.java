package com.org.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.utils.ApiResponse;

@RestController
public class HomeController {
	
	@GetMapping("/test")
	public ResponseEntity<ApiResponse<String>> Home() {
		
		String msg = "Your Application Running Properly";
		
		ApiResponse<String> response = new ApiResponse<String>(200,msg,"Good");
		
		return ResponseEntity.status(201).body(response);
	}

}
