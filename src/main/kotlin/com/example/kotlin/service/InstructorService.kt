package com.example.kotlin.service

import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.entity.Instructor
import com.example.kotlin.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(val instructorRepository: InstructorRepository) {

    fun addNewInstructor(instructorDTO: InstructorDTO): InstructorDTO {

        val instructorEntity = instructorDTO.let {
            Instructor(it.id, it.name)
        }

        instructorRepository.save(instructorEntity)

        return instructorEntity.let {
            InstructorDTO(it.id, it.name)
        }
    }

    fun findInstructorById(instructorId: Int): Optional<Instructor> {

        return  instructorRepository.findById(instructorId)
    }
}
