package com.cdac.E_Learning.controller;

import com.cdac.E_Learning.pojo.UserVideoProgress;
import com.cdac.E_Learning.service.IUserVideoProgressService;
import com.cdac.E_Learning.dto.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userVideoProgress")
@CrossOrigin("*")
public class UserVideoProgressController {

    @Autowired
    private IUserVideoProgressService userVideoProgressService;

    @PostMapping("/record")
    public ResponseEntity<APIResponse<UserVideoProgress>> recordProgress(
            @RequestParam("userId") Long userId,
            @RequestParam("videoId") Long videoId,
            @RequestParam("progress") int progress) {

        UserVideoProgress userVideoProgress = userVideoProgressService.recordProgress(userId, videoId, progress);
        return ResponseEntity.ok(new APIResponse<>("success", userVideoProgress, "Progress recorded successfully"));
    }

//    @GetMapping("/viewCourseProgress")
//    public ResponseEntity<APIResponse<List<UserVideoProgress>>> getProgressByUserIdAndCourseId(
//            @RequestParam("userId") Long userId,
//            @RequestParam("courseId") Long courseId) {
//
//        List<UserVideoProgress> progressList = userVideoProgressService.getProgressByUserIdAndCourseId(userId, courseId);
//        return ResponseEntity.ok(new APIResponse<>("success", progressList, "Progress retrieved successfully"));
//    }
//
//    @GetMapping("/videoProgress")
//    public ResponseEntity<APIResponse<UserVideoProgress>> getProgressByUserIdAndVideoId(
//            @RequestParam("userId") Long userId,
//            @RequestParam("videoId") Long videoId) {
//
//        UserVideoProgress progress = userVideoProgressService.getProgressByUserIdAndVideoId(userId, videoId);
//        return ResponseEntity.ok(new APIResponse<>("success", progress, "Progress retrieved successfully"));
//    }
    
    @GetMapping("/courseProgress")
    public ResponseEntity<APIResponse<Double>> getCourseProgress(
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId) {

        double progress = userVideoProgressService.calculateCourseProgress(userId, courseId);
        return ResponseEntity.ok(new APIResponse<>("success", progress, "Course progress retrieved successfully"));
    }
}
