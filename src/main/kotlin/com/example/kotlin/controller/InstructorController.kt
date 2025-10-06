package com.example.kotlin.controller


import com.example.kotlin.dto.InstructorDTO
import com.example.kotlin.service.InstructorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/instructors")
class InstructorController(val instructorService: InstructorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstructor(@RequestBody instructorDTO: InstructorDTO): InstructorDTO
            = instructorService.addNewInstructor(instructorDTO)

}
