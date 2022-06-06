package com.example.lab2sping.user;

import com.example.lab2sping.faculty.Faculty;
import com.example.lab2sping.faculty.FacultyRepository;
import com.example.lab2sping.submission.Submission;
import com.example.lab2sping.submission.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final FacultyRepository facultyRepository;

    public UserService(UserRepository userRepository, SubmissionRepository submissionRepository,
                       FacultyRepository facultyRepository){
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new IllegalStateException("No user with id = " + id);
        }
        return user.get();
    }

    public User getUserByLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);

        if(!user.isPresent()){
            throw new IllegalStateException("No user with login = " + login);
        }
        return user.get();
    }

    public void addUser(User user){
        Optional<User> userById = userRepository.findById(user.getId());
        if(userById.isPresent()) {
            throw new IllegalStateException("User with id = " + user.getId() + " already exists");
        }

        userRepository.save(user);
    }

    public void blockUser(Long id) {
        User user = userRepository.findById(id).get();

        user.setRole("BLOCKED");
        userRepository.save(user);

    }

    public void unblockUser(Long id) {
        User user = userRepository.findById(id).get();

        user.setRole("USER");
        userRepository.save(user);

    }

    public List<Faculty> getAllUnsubmittedFaculties(User user) {
        List<Faculty> res = facultyRepository.findAll();
        for(Submission s: user.getSubmissions()){
            res.remove(s.getFaculty());
        }
        
        return res;
    }

    public List<Submission> findAllSubmissionsForUser(User user){
        return submissionRepository.findAllForUser(user);
    }
}
