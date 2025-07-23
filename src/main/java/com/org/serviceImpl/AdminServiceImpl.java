package com.org.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.org.entity.Candidate;
import com.org.repository.CandidateRepository;
import com.org.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private CandidateRepository candidateRepo;

	 
	public List<Candidate> getAllCandidate() {
		
		List<Candidate> clist = candidateRepo.findAll();
		return clist;
	}


}
