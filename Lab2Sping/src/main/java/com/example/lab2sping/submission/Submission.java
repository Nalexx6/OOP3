package com.example.lab2sping.submission;

import com.example.lab2sping.user.User;
import com.example.lab2sping.faculty.Faculty;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
public class Submission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Faculty faculty;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    private List<Integer> grades;
    private Double secEducAvg;
    private boolean checked;
    private Integer finalizationStatus;

    public Submission(){
        finalizationStatus = -1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public Double getSecEducAvg() {
        return secEducAvg;
    }

    public void setSecEducAvg(Double secEducAvg) {
        this.secEducAvg = secEducAvg;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getFinalizationStatus() {
        return finalizationStatus;
    }

    public void setFinalizationStatus(Integer finalizationStatus) {
        this.finalizationStatus = finalizationStatus;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Submission)){
            return false;
        }

        Submission submission = (Submission) o;
        return  faculty.equals(submission.getFaculty()) &&
                user.equals(submission.getUser()) &&
                grades.get(0).equals(submission.getGrades().get(0)) &&
                grades.get(1).equals(submission.getGrades().get(1)) &&
                grades.get(2).equals(submission.getGrades().get(2)) &&
                secEducAvg.equals(submission.getSecEducAvg()) &&
                checked == submission.isChecked() &&
                finalizationStatus.equals(submission.getFinalizationStatus());
    }


}
