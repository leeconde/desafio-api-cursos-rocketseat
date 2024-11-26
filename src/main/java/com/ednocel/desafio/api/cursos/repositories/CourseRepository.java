package com.ednocel.desafio.api.cursos.repositories;

import com.ednocel.desafio.api.cursos.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> getAllByName(String name);

    @Query("SELECT c FROM course c INNER JOIN category ca ON  c.category_id = ca.id WHERE ca.name = :categoryName")
    List<CourseEntity> findByCategoryName(@Param("categoryName") String categoryName);
}

