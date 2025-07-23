package com.org.service;

import com.org.entity.Candidate;

public interface CandidateService {

	Candidate register(Candidate data);

	public Candidate viewProfile(Integer id);
}
