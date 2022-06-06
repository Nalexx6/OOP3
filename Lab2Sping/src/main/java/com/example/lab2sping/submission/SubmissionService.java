package com.example.lab2sping.submission;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository){
        this.submissionRepository = submissionRepository;
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(Long id){
        Optional<Submission> submission = submissionRepository.findById(id);

        if(!submission.isPresent()){
            throw new IllegalStateException("No submission with id = " + id);
        }
        return submission.get();
    }

    public void addSubmission(Submission submission){
        Optional<Submission> submissionById = submissionRepository.findById(submission.getId());
        if(submissionById.isPresent()) {
            throw new IllegalStateException("Submission with id = " + submission.getId() + " already exists");
        }

        submissionRepository.save(submission);
    }

    public void checkSubmission(Long id){
        Submission submission = submissionRepository.findById(id).get();

        submission.setChecked(true);
        submissionRepository.save(submission);
    }

    public void finalizeSubmission(Long id, int status){
        Submission submission = submissionRepository.findById(id).get();

        submission.setChecked(true);
        submission.setFinalizationStatus(status);
        submissionRepository.save(submission);
    }
}
