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

	@Override
	public Candidate register(Candidate data) {
		
		Candidate c = candidateRepo.save(data);
		return c;		
		
	}

<<<<<<< HEAD

=======
//
//<<<<<<< HEAD
>>>>>>> 55fd485d7b6c2f87dfea39f0fcf0850b46c0b1a9
	@Override
	public Candidate viewProfile(Integer id) {
		 return candidateRepo.findById(id).get();
	}
<<<<<<< HEAD
		 
=======
	
>>>>>>> 55fd485d7b6c2f87dfea39f0fcf0850b46c0b1a9
	public List<Candidate> getAllCandidate() {
		
		List<Candidate> clist = candidateRepo.findAll();
		return clist;
	}
//>>>>>>> 9a98d3943aa4c5495d7a802436b1067199b30f18


}
