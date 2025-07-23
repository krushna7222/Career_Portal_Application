package com.org.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String password;
	@NonNull
	private Long phone;
	@NonNull
	private String gender;
	@NonNull
	private Date dob;
	@NonNull
	private String education;
	@NonNull
	private Integer passoutYear;
	@NonNull
	private String status;
	@NonNull
	private String profileSummary;
	@NonNull
	private String workExperience;
	@NonNull
	private String skills;
	@NonNull
	private String resume;
	@NonNull
	private String photo;
}
