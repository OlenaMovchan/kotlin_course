package com.example.kotlin.service

import com.example.kotlin.converter.instructor.InstructorDtoToEntityConverter
import com.example.kotlin.converter.instructor.InstructorToDtoConverter
import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.entity.Instructor
import com.example.kotlin.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(
    val instructorRepository: InstructorRepository,
    val instructorDtoToEntityConverter: InstructorDtoToEntityConverter,
    val instructorToDtoConverter: InstructorToDtoConverter) {

    fun addNewInstructor(instructorDTO: InstructorDTO): InstructorDTO {

        val instructorEntity = instructorDtoToEntityConverter.convert(instructorDTO)

        val savedInstructor = instructorRepository.save(instructorEntity)

        return instructorToDtoConverter.convert(savedInstructor)
    }

    fun findInstructorById(instructorId: Int): Optional<Instructor> {

        return instructorRepository.findById(instructorId)
    }
}
