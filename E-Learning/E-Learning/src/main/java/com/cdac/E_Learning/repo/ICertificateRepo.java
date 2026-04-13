package com.cdac.E_Learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.E_Learning.pojo.Certificate;

public interface ICertificateRepo extends JpaRepository<Certificate, Long> {

}
