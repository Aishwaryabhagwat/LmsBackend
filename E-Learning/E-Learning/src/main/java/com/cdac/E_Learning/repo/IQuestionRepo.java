package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.E_Learning.pojo.Question;

@Repository
public interface IQuestionRepo  extends JpaRepository<Question, Long>{

	 List<Question> findByQuizId(Long id);
	  List<Question> findByQuizIdAndIsActive(Long quizId, int isActive);
}
