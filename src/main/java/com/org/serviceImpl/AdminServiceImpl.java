package com.org.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.entity.Candidate;
import com.org.repository.CandidateRepository;
import com.org.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private CandidateRepository candidateRepo;

	 
	public List<Candidate> getAllCandidate() {
		
		List<Candidate> clist = candidateRepo.findAll();
		return clist;
		
		
	}


}
