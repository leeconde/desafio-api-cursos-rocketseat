package com.ednocel.desafio.api.cursos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavedCourseResponse {

    private String name;

    private String category;

    private LocalDateTime createdAt;

}
