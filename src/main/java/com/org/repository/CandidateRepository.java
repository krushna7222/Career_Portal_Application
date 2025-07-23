package com.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
