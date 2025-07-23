package com.org.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.entity.Candidate;
import com.org.repository.CandidateRepository;
import com.org.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	@Autowired
    private CandidateRepository candidateRepo;


	public Candidate register(Candidate data) {
		
		Candidate c = candidateRepo.save(data);
		return c;		
		
	}


	public List<Candidate> getAllCandidate() {
		
		List<Candidate> clist = candidateRepo.findAll();
		return clist;
	}


}
