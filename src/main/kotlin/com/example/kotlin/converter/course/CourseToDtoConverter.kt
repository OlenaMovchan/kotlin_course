package com.example.kotlin.converter.course

import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.entity.Course
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CourseToDtoConverter : Converter<Course, CourseDTO> {
    override fun convert(source: Course): CourseDTO {
        return CourseDTO(
            id = source.id,
            name = source.name,
            category = source.category,
            instructorId = source.instructor?.id
        )
    }
}