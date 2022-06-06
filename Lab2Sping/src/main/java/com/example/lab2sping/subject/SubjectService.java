package com.example.lab2sping.subject;

import com.example.lab2sping.faculty.Faculty;
import com.example.lab2sping.faculty.FacultyRepository;
import com.example.lab2sping.submission.SubmissionRepository;

import java.util.List;
import java.util.Optional;

public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Subject getSubjectByName(String name){
        Optional<Subject> subject = subjectRepository.findByName(name);

        if(!subject.isPresent()){
            throw new IllegalStateException("No subject with name = " + name);
        }
        return subject.get();
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();

    }
}
