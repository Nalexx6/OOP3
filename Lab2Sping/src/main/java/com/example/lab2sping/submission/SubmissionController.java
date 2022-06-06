package com.example.lab2sping.submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SubmissionController {
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService){
        this.submissionService = submissionService;
    }

    @GetMapping
    public List<Submission> getSubmissions(){
        return submissionService.getAllSubmissions();
    }

    @GetMapping(path="{submissionId}")
    public Submission getSubmissionById(@PathVariable("submissionId") Long submissionId){
        return submissionService.getSubmissionById(submissionId);
    }

    @PostMapping
    public void addNewSubmission(@RequestBody Submission submission){
        submissionService.addSubmission(submission);
    }

    @PutMapping(path="check/{submissionId}")
    public void checkSubmission(@PathVariable("submissionId") Long submissionId){
        submissionService.checkSubmission(submissionId);
    }

    @PutMapping(path="{submissionId}/{status}")
    public void finalizeSubmission(
            @PathVariable("submissionId") Long submissionId,
            @RequestBody Integer status){
        submissionService.finalizeSubmission(submissionId, status);
    }
}
