package com.org.service;

import java.util.List;
import java.util.Map;

import com.org.entity.Candidate;

public interface AdminService {
	
	String refreshAccessToken(String refreshToken);
	
	Map<String, String> login(String email, String password);
	
	List<Candidate> getAllCandidate();

}
