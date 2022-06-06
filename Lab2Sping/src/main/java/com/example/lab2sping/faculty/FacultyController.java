package com.example.lab2sping.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
    }

    @GetMapping(path="{facultyId}")
    public Faculty GetFacultyById(@PathVariable("facultyId") Long facultyId){
        return facultyService.getFacultyById(facultyId);
    }

    @GetMapping(path="{facultyName}")
    public Faculty GetFacultyById(@PathVariable("facultyName") String facultyName){
        return facultyService.getFacultyByName(facultyName);
    }

    @PostMapping
    public void registerNewFaculty(@RequestBody Faculty faculty, @RequestHeader("Authorization") String authString){
        facultyService.addFaculty(faculty);
    }

    @DeleteMapping(path="{facultyId}")
    public void deleteFaculty(@PathVariable("facultyId") Long facultyId){
        facultyService.deleteFaculty(facultyId);
    }

    @PutMapping(path="{facultyId}/{faculty}")
    public void updateFaculty(
            @PathVariable("facultyId") Long facultyId,
            @RequestBody Faculty faculty){
        facultyService.updateFaculty(facultyId, faculty);
    }
}
