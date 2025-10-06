package com.example.kotlin.converter.course

import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.entity.Course
import com.example.kotlin.entity.Instructor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CourseDtoToEntityConverter : Converter<CourseDTO, Course> {
    override fun convert(source: CourseDTO): Course {
        return Course(
            id = source.id,
            name = source.name,
            category = source.category,
            instructor = Instructor(id = source.instructorId, name = source.name)
        )
    }
}

