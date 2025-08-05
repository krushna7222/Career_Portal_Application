package com.org.serviceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.org.entity.Candidate;
import com.org.repository.CandidateRepository;
import com.org.service.CandidateService;
import com.org.utils.CloudinaryUtil;

import lombok.NonNull;

@Service
public class CandidateServiceImpl implements CandidateService {
	
	@Autowired
    private CandidateRepository candidateRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CloudinaryUtil cloudinaryUtil;

	@Override
	public Candidate register(String name, String email, String password, Long phone, String gender, Date dob,
	                          String education, Integer passoutYear, String status, String profileSummary,
	                          String workExperience, String skills, MultipartFile resumeFile, MultipartFile photoFile) {

	    try {
	        File resume = cloudinaryUtil.convertToFile(resumeFile.getOriginalFilename(), resumeFile.getBytes());
	        File photo = cloudinaryUtil.convertToFile(photoFile.getOriginalFilename(), photoFile.getBytes());

	        String resumeUrl = cloudinaryUtil.uploadFile(resume, "career_portal/resumes");
	        String photoUrl = cloudinaryUtil.uploadFile(photo, "career_portal/photos");

	        Candidate candidate = new Candidate();
	        candidate.setName(name);
	        candidate.setEmail(email);
	        candidate.setPassword(passwordEncoder.encode(password));
	        candidate.setPhone(phone);
	        candidate.setGender(gender);
	        candidate.setDob(dob);
	        candidate.setEducation(education);
	        candidate.setPassoutYear(passoutYear);
	        candidate.setStatus(status);
	        candidate.setProfileSummary(profileSummary);
	        candidate.setWorkExperience(workExperience);
	        candidate.setSkills(skills);
	        candidate.setResume(resumeUrl);
	        candidate.setPhoto(photoUrl);

	        return candidateRepository.save(candidate);

	    } catch (Exception e) {
	        throw new RuntimeException("Registration failed: " + e.getMessage());
	    }
	}


	@Override
	public Candidate viewProfile(Integer id) {
		
		 return candidateRepository.findById(id).get();
	}

	@Override
	public Candidate findByEmail(@NonNull String email) {
		return candidateRepository.findByEmail(email).orElse(null);
	}


}
