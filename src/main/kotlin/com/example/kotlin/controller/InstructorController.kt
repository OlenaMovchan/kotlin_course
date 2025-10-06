package com.example.kotlin.controller

import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.service.InstructorService
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(summary = "Add new instructor", description = "Creates a new instructor and returns it")
    fun createInstructor(@RequestBody @Valid instructorDTO: InstructorDTO): InstructorDTO
            = instructorService.addNewInstructor(instructorDTO)

}
