package com.example.lab2sping.subject;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Subject)){
            return false;
        }

        Subject subject = (Subject) o;
        return name.equals(subject.getName());
    }
}
