package com.example.lab2sping.submission;

import com.example.lab2sping.faculty.Faculty;
import com.example.lab2sping.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findAllForUser(User user);
    List<Submission> findAllForFaculty(Faculty faculty);
    void deleteAllForFaculty(Faculty faculty);

}
