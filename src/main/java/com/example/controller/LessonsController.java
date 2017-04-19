package com.example.controller;

import com.example.entity.Lesson;
import com.example.repositories.LessonRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Lesson getById(@PathVariable long id) {
        return this.repository.findOne(id);
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Lesson> update(@PathVariable long id, @RequestBody Lesson lesson) {
        if (null != this.repository.findOne(id))
            return new ResponseEntity<>(this.repository.save(lesson), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        try {
            this.repository.delete(id);
            return new ResponseEntity("", HttpStatus.OK);
        } catch (EmptyResultDataAccessException erde) {
            return new ResponseEntity("id does not exist!", HttpStatus.BAD_REQUEST);
        }
    }

}