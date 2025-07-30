package com.org.service;

import com.org.entity.Candidate;

import lombok.NonNull;

public interface CandidateService {

	Candidate register(Candidate data);
	
	Candidate findByEmail(@NonNull String email);

	Candidate viewProfile(Integer id);
}
