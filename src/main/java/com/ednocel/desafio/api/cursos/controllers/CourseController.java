package com.ednocel.desafio.api.cursos.controllers;

import com.ednocel.desafio.api.cursos.entities.CourseDto;
import com.ednocel.desafio.api.cursos.entities.PatchCouseRequest;
import com.ednocel.desafio.api.cursos.entities.UpdateCourseRequest;
import com.ednocel.desafio.api.cursos.exceptions.InvalidParametersException;
import com.ednocel.desafio.api.cursos.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseDto courseDto) {
        try {
            var response = this.courseService.save(courseDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<Object> getAll() {
        try {
            var response = this.courseService.getAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/courses/name")
    public ResponseEntity<Object> getAllByName(@RequestParam String name) {
        try {
            var response = this.courseService.getAllByName(name);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/courses/category")
    public ResponseEntity<Object> getAllByCategory(@RequestParam String category) {
        try {
            var response = this.courseService.getAllByCategory(category);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateCourseRequest request, @PathVariable Long id) {
        try {
            if (request.getCategory() == null && request.getName() == null) {
                throw new InvalidParametersException();
            }
            this.courseService.update(request, id);
            return ResponseEntity.ok("Course has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            this.courseService.delete(id);
            return ResponseEntity.ok("Course has been deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patch(@PathVariable Long id, @RequestBody PatchCouseRequest request) {
        try {
            if (request.getActive() == null) {
                throw new InvalidParametersException();
            }
            this.courseService.patch(id, request.getActive());
            return ResponseEntity.ok("Course has been patch successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
