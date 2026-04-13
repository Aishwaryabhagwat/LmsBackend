package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.E_Learning.pojo.EnrollmentRequest;

public interface EnrollmentRequestRepository extends JpaRepository<EnrollmentRequest, Long> {
    List<EnrollmentRequest> findByStatus(String status);
}