package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.E_Learning.pojo.EnrollmentRequest;

@Repository
public interface IEnrollmentRequestRepository extends JpaRepository<EnrollmentRequest, Long> {
    List<EnrollmentRequest> findByStatus(String status);
}
