package com.cdac.E_Learning.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.E_Learning.dto.ChapterRequestDTO;
import com.cdac.E_Learning.dto.QuizDTO;
import com.cdac.E_Learning.pojo.Chapter;
import com.cdac.E_Learning.repo.IChapterRepo;
import com.cdac.E_Learning.repo.IQuizRepository;

import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.Question;
import com.cdac.E_Learning.pojo.Quiz;
import com.cdac.E_Learning.pojo.QuizOption;



@Service
public class ChapterServiceImpl implements IChapterService {

    @Autowired
    private IChapterRepo chapterRepo;
    
    @Autowired
    private IQuizRepository quizRepository; 
    
   
    
    @Override
	public List<Chapter> getAllChapter() {
		
		return chapterRepo.findByIsActive(1);
	}


    
    @Override
    public List<Chapter> getChaptersByCourseId(Long courseId) {
        List<Chapter> chapters = chapterRepo.findByCourseId(courseId);
        for (Chapter chapter : chapters) {
            // Fetch active quizzes for each chapter
            List<Quiz> quizzes = quizRepository.findByChapterIdAndIsActive(chapter.getId(), 1);
            chapter.setQuizzes(quizzes); // Set the quizzes for the chapter
        }
        System.out.println(chapters);
        return chapters;
    }

    
//    @Override
//    public List<Chapter> getChaptersByCourseId(Long courseId, Long userId) {
//        List<Chapter> chapters = chapterRepo.findByCourseId(courseId);
//        for (Chapter chapter : chapters) {
//            // Fetch all active quizzes for each chapter
//            List<Quiz> quizzes = quizRepository.findByChapterIdAndIsActive(chapter.getId(), 1);
//            
//            // Check quiz attempt status for the user
//            for (Quiz quiz : quizzes) {
//                UserQuiz userQuiz = userQuizRepo.findByUserIdAndQuizId(userId, quiz.getId());
//                if (userQuiz != null) {
//                    quiz.setAttempted(true); // Mark as attempted
//                    quiz.setScore(userQuiz.getScore()); // Add latest score
//                } else {
//                    quiz.setAttempted(false); // Mark as not attempted
//                    quiz.setScore(null); // No score
//                }
//            }
//            chapter.setQuizzes(quizzes); // Set quizzes for the chapter
//        }
//        return chapters;
//    }

    public List<Quiz> getQuizzesByChapterId(Long chapterId, Long userId) {
        return quizRepository.findQuizzesWithUserStatus(chapterId, userId);
    }




 


    
    @Override
    public Chapter addChapter(ChapterRequestDTO chapterRequest) {
        Chapter chapter = new Chapter();
        chapter.setTitle(chapterRequest.getTitle());
        chapter.setDescription(chapterRequest.getDescription());
        // Set course using course ID from request
        Course course = new Course();
        course.setId(chapterRequest.getCourseId());
        chapter.setCourse(course);
        chapter.setIsActive(1);
        return chapterRepo.save(chapter);
    }
    
    public Chapter updateChapter(Long id, Chapter chapter) {
        Optional<Chapter> chapterOpt = chapterRepo.findById(id);
        if (!chapterOpt.isPresent()) {
            throw new IllegalArgumentException("Chapter with ID: " + id + " not found");
        }
        Chapter existingChapter = chapterOpt.get();
        existingChapter.setTitle(chapter.getTitle());
        existingChapter.setDescription(chapter.getDescription());
       
        return chapterRepo.save(existingChapter);
    }
    
    public void deleteChapter(Long id) {
        Optional<Chapter> chapterOpt = chapterRepo.findById(id);
        if (!chapterOpt.isPresent()) {
            throw new IllegalArgumentException("Chapter with ID: " + id + " not found");
        }
        Chapter chapter = chapterOpt.get();
        
        // Soft delete: set isActive to 0
        chapter.setIsActive(0);
        
        chapterRepo.save(chapter);
    }





	@Override
	public List<Chapter> getChaptersByCourseId(Long courseId, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Chapter> getChaptersByCourseAndUser(Long courseId, Long userId) {
	    // Fetch chapters by course ID
        List<Chapter> chapters = chapterRepo.findByCourseId(courseId);

        // For each chapter, fetch quizzes and enrich with user-specific data
        for (Chapter chapter : chapters) {
            List<Quiz> quizzes = quizRepository.findQuizzesWithUserStatus(chapter.getId(), userId);
            
            // Filter quizzes to include only active ones and remove duplicates by quiz ID
            List<Quiz> uniqueActiveQuizzes = quizzes.stream()
                    .filter(quiz -> quiz.getIsActive() == 1) // Keep only active quizzes
                    .filter(distinctByKey(Quiz::getId)) // Remove duplicates based on quiz ID
                    .collect(Collectors.toList());
            
            chapter.setQuizzes(uniqueActiveQuizzes); // Attach enriched quizzes to the chapter
        
        }

        return chapters;
	}



	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
}




	
}
