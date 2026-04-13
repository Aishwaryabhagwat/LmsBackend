package com.cdac.E_Learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.E_Learning.pojo.User;

public interface IUserCourseRepo  extends JpaRepository<User, Long>{

}
