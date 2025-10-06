package com.example.kotlin.converter.instructor

import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.entity.Instructor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class InstructorToDtoConverter : Converter<Instructor, InstructorDTO> {
    override fun convert(source: Instructor): InstructorDTO {
        return InstructorDTO(
            id = source.id,
            name = source.name
        )
    }
}