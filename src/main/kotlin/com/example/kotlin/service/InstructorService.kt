package com.example.kotlin.service

import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.entity.Instructor
import com.example.kotlin.repository.InstructorRepository
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(
    val instructorRepository: InstructorRepository,
    val converterService: ConversionService,
    ) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun addNewInstructor(instructorDTO: InstructorDTO): InstructorDTO {

        val instructorEntity = converterService.convert(instructorDTO, Instructor::class.java)
            ?: throw IllegalStateException("Conversion from InstructorDTO to entity failed")

        val savedInstructor = instructorRepository.save(instructorEntity)
        logger.info("Saved instructor is : $savedInstructor")
        return converterService.convert(savedInstructor, InstructorDTO::class.java)
            ?: throw IllegalStateException("Conversion from entity to InstructorDTO failed")
    }

    fun findInstructorById(instructorId: Int): Optional<Instructor> {

        return instructorRepository.findById(instructorId)
    }
}
