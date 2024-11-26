package com.ednocel.desafio.api.cursos.services;

import com.ednocel.desafio.api.cursos.entities.*;
import com.ednocel.desafio.api.cursos.exceptions.CourseAlreadyActiveOrInactiveException;
import com.ednocel.desafio.api.cursos.exceptions.CourseWasNotFoundException;
import com.ednocel.desafio.api.cursos.repositories.CategoryRepository;
import com.ednocel.desafio.api.cursos.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public SavedCourseResponse save(CourseDto courseDto) {

        CategoryEntity category = getCategory(courseDto.getCategory());

        var courseEntity = CourseEntity.builder()
                .active(true)
                .category_id(category.getId())
                .categoryEntity(category)
                .name(courseDto.getName())
                .build();

        var savedCourse = this.courseRepository.save(courseEntity);

        return SavedCourseResponse.builder()
                .name(savedCourse.getName())
                .category(savedCourse.getCategoryEntity().getName())
                .createdAt(savedCourse.getCreatedAt())
                .build();
    }

    public List<CourseResponse> getAll() {
        List<CourseEntity> list = this.courseRepository.findAll();
        List<CourseResponse> response = new ArrayList<>();

        if (list.isEmpty()) {
            list = new ArrayList<>();
        }

        for (CourseEntity c : list) {
            var course = CourseResponse.builder()
                    .name(c.getName())
                    .category(c.getCategoryEntity().getName())
                    .updatedAt(c.getUpdatedAt())
                    .createdAt(c.getCreatedAt())
                    .active(c.getActive())
                    .build();

            response.add(course);
        }
        return response;
    }

    public List<CourseResponse> getAllByName(String name) {
        var list = this.courseRepository.getAllByName(name);
        List<CourseResponse> response = new ArrayList<>();

        if (list.isEmpty()) {
            list = new ArrayList<>();
        }

        for (CourseEntity c : list) {
            var course = CourseResponse.builder()
                    .name(c.getName())
                    .category(c.getCategoryEntity().getName())
                    .updatedAt(c.getUpdatedAt())
                    .createdAt(c.getCreatedAt())
                    .active(c.getActive())
                    .build();

            response.add(course);
        }
        return response;
    }

    public List<CourseResponse> getAllByCategory(String category) {
        var list = this.courseRepository.findByCategoryName(category);
        List<CourseResponse> response = new ArrayList<>();

        if (list.isEmpty()) {
            list = new ArrayList<>();
        }

        for (CourseEntity c : list) {
            var course = CourseResponse.builder()
                    .name(c.getName())
                    .category(c.getCategoryEntity().getName())
                    .updatedAt(c.getUpdatedAt())
                    .createdAt(c.getCreatedAt())
                    .active(c.getActive())
                    .build();

            response.add(course);
        }
        return response;
    }

    public void update(UpdateCourseRequest request, Long id) {
        var course = this.courseRepository.findById(id).orElseThrow(CourseWasNotFoundException::new);

        if (request.getName() != null) {
            course.setName(request.getName());
        }

        if (request.getCategory() != null) {
            var category = getCategory(request.getCategory());
            course.setCategoryEntity(category);
            course.setCategory_id(category.getId());
        }

        this.courseRepository.save(course);
    }


    public void delete(Long id) {
        this.courseRepository.deleteById(id);
    }

    public void patch(Long id, Boolean active) {
        var course = this.courseRepository.findById(id).orElseThrow(CourseWasNotFoundException::new);

        if (active.equals(course.getActive())) {
            throw new CourseAlreadyActiveOrInactiveException(active.toString());
        }

        course.setActive(active);
        this.courseRepository.save(course);
    }

    public CategoryEntity getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseGet(() -> categoryRepository.save(
                        CategoryEntity.builder().name(categoryName).build()));
    }
}
