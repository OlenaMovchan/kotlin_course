package com.example.kotlin.controller

import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.service.InstructorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/instructors")
@Validated
class InstructorController(val instructorService: InstructorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstructor(@RequestBody @Valid instructorDTO: InstructorDTO): InstructorDTO
            = instructorService.addNewInstructor(instructorDTO)

}
