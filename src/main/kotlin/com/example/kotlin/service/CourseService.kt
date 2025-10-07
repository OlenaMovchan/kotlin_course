package com.example.kotlin.service

import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.entity.Course
import com.example.kotlin.exception.CourseNotFoundException
import com.example.kotlin.exception.InstructorNotValidException
import com.example.kotlin.repository.CourseRepository
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val instructorService: InstructorService,
    val converterService: ConversionService,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val instructor = instructorService.findInstructorById(courseDTO.instructorId
            ?: throw IllegalArgumentException("Instructor ID is required"))
            .orElseThrow { InstructorNotValidException("Instructor Not Valid for the Id: ${courseDTO.instructorId}") }

        val courseEntity = converterService.convert(courseDTO, Course::class.java)
            ?.apply { this.instructor = instructor }
            ?: throw IllegalStateException("Conversion from CourseDTO to entity failed")

        val savedCourse = courseRepository.save(courseEntity)
        logger.info("Saved course is : $savedCourse")

        return converterService.convert(savedCourse, CourseDTO::class.java)
            ?: throw IllegalStateException("Conversion from entity to CourseDTO failed")
    }

    fun retrieveAllCourses(courseName: String?): List<CourseDTO> {
        val courses = courseName?.let {
            courseRepository.findCoursesByName("%$courseName%")
        } ?: courseRepository.findAll()
        return courses
            .map { course ->
                converterService.convert(course, CourseDTO::class.java) as CourseDTO
            }
    }

    fun updateCourse(courseId: Int, courseDTO: CourseDTO): CourseDTO {
        val existingCourse = courseRepository.findById(courseId)
            .orElseThrow { CourseNotFoundException("No Course Found for the passed in Id $courseId") }

        existingCourse.name = courseDTO.name
        existingCourse.category = courseDTO.category

        val savedCourse = courseRepository.save(existingCourse)
        logger.info("Updated course is : $savedCourse")
        return converterService.convert(savedCourse, CourseDTO::class.java) as CourseDTO
    }


    fun deleteCourse(courseId: Int) {
        val existingCourse = courseRepository.findById(courseId)
            .orElseThrow() { CourseNotFoundException("No Course Found for the Id $courseId") }
        logger.info("Deleted course is : $existingCourse")
        courseRepository.delete(existingCourse)

    }
}
