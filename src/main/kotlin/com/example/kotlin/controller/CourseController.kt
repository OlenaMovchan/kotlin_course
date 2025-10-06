package com.example.kotlin.controller

import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.service.CourseService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new course", description = "Creates a new course and returns it")
    fun addCourse(@RequestBody @Valid courseDTO: CourseDTO): CourseDTO {
        return courseService.addCourse(courseDTO)
    }

    @GetMapping
    @Operation(summary = "Get all courses", description = "Returns a list of courses")
    fun retrieveAllCourses(@RequestParam("course_name", required = false) courseName: String?): List<CourseDTO> =
        courseService.retrieveAllCourses(courseName)

    @PutMapping("/{course_id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update course", description = "Updates an existing course by ID")
    fun updateCourse(
        @RequestBody courseDTO: CourseDTO,
        @PathVariable("course_id") courseId: Int
    ): CourseDTO = courseService.updateCourse(courseId, courseDTO)

    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete course", description = "Deletes a course by ID")
    fun deleteCourse(@PathVariable("course_id") courseId: Int) {
        courseService.deleteCourse(courseId)

    }
}
