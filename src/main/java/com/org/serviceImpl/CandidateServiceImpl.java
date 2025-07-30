package com.org.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.entity.Candidate;
import com.org.repository.CandidateRepository;
import com.org.service.CandidateService;

import lombok.NonNull;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	@Autowired
    private CandidateRepository candidateRepository;

	@Override
	public Candidate register(Candidate data) {
		
		Candidate c = candidateRepository.save(data);
		return c;		
		
	}

	@Override
	public Candidate viewProfile(Integer id) {
		
		 return candidateRepository.findById(id).get();
	}

	public Candidate findByEmail(@NonNull String email) {
		return candidateRepository.findByEmail(email).orElse(null);
	}


}
