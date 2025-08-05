package com.org.service;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.org.entity.Candidate;

import lombok.NonNull;

public interface CandidateService {

	public Candidate register(String name, String email, String password, Long phone, String gender, Date dob,
            String education, Integer passoutYear, String status, String profileSummary,
            String workExperience, String skills, MultipartFile resumeFile, MultipartFile photoFile);
	
	Candidate findByEmail(@NonNull String email);

	Candidate viewProfile(Integer id);
}
