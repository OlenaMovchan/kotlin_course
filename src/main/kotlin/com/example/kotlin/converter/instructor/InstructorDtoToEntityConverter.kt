package com.example.kotlin.converter.instructor

import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.entity.Instructor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class InstructorDtoToEntityConverter : Converter<InstructorDTO, Instructor> {
    override fun convert(source: InstructorDTO): Instructor {
        return Instructor(
            id = source.id,
            name = source.name,
            courses = mutableListOf()
        )
    }
}

