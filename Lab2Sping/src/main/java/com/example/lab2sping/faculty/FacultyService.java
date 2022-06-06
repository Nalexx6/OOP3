package com.example.lab2sping.faculty;

import com.example.lab2sping.submission.Submission;
import com.example.lab2sping.submission.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final SubmissionRepository submissionRepository;

    public FacultyService(FacultyRepository facultyRepository, SubmissionRepository submissionRepository){
        this.facultyRepository = facultyRepository;
        this.submissionRepository = submissionRepository;
    }

    public Faculty getFacultyByName(String name) {
        Optional<Faculty> faculty = facultyRepository.findByName(name);

        if(!faculty.isPresent()){
            throw new IllegalStateException("No faculty with name = " + name);
        }
        return faculty.get();
    }

    public Faculty getFacultyById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);

        if(!faculty.isPresent()){
            throw new IllegalStateException("No faculty with id = " + id);
        }
        return faculty.get();
    }

    public void addFaculty(Faculty faculty){
        Optional<Faculty> facultyById = facultyRepository.findById(faculty.getId());
        if(facultyById.isPresent()) {
            throw new IllegalStateException("Faculty with id = " + faculty.getId() + " already exists");
        }
        Optional<Faculty> facultyByName = facultyRepository.findByName(faculty.getName());
        if(facultyByName.isPresent()){
            throw new IllegalStateException("Faculty with name = " + faculty.getName() + " already exists");
        }

        facultyRepository.save(faculty);

    }

    public void updateFaculty(Long facultyid, Faculty newFaculty){
        Faculty faculty = facultyRepository.findById(facultyid).orElseThrow(() -> new IllegalStateException(
                "Faculty with id = " + facultyid + " doesn't exist"
        ));

        faculty.setName(newFaculty.getName());
        faculty.setStudentsAmount(newFaculty.getStudentsAmount());
        faculty.setStateFundedAmount(newFaculty.getStateFundedAmount());
        faculty.setSubjects(newFaculty.getSubjects());
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id){
        Optional<Faculty> faculty = facultyRepository.findById(id);

        if(faculty.isPresent()){
            facultyRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Faculty with id = " + id + " doesn't exist.");
        }

        submissionRepository.deleteAllForFaculty(faculty.get());

    }

    public List<Submission> findAllSubmissionsForFaculty(Faculty faculty){
        return submissionRepository.findAllForFaculty(faculty);
    }
}
