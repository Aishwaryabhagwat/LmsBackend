package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.E_Learning.pojo.QuizOption;

public interface QuizOptionRepository extends JpaRepository<QuizOption, Long>{

	  List<QuizOption> findByQuestionId(Long questionId);
}
