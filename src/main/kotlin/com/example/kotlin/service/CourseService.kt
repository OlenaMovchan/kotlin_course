package com.example.kotlin.service

import com.example.kotlin.converter.course.CourseDtoToEntityConverter
import com.example.kotlin.converter.course.CourseToDtoConverter
import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.exception.CourseNotFoundException
import com.example.kotlin.exception.InstructorNotValidException
import com.example.kotlin.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val instructorService: InstructorService,
    val courseDtoToEntityConverter: CourseDtoToEntityConverter,
    val courseEntityToDtoConverter: CourseToDtoConverter,
) {

    companion object : KLogging()

    fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val instructorOptional = instructorService.findInstructorById(courseDTO.instructorId!!)
        if (!instructorOptional.isPresent) {
            throw InstructorNotValidException("Instructor Not Valid for the Id: ${courseDTO.instructorId}")
        }

        val courseEntity = courseDtoToEntityConverter.convert(courseDTO)
        courseEntity.instructor = instructorOptional.get()
        val savedCourse = courseRepository.save(courseEntity)
        logger.info("Saved course is : $savedCourse")

        return courseEntityToDtoConverter.convert(savedCourse)
    }

    fun retrieveAllCourses(courseName: String?): List<CourseDTO> {
        val courses = courseName?.let {
            courseRepository.findCoursesByName(courseName)
        } ?: courseRepository.findAll()
        return courses
            .map {
                courseEntityToDtoConverter.convert(it)
            }
    }

    fun updateCourse(courseId: Int, courseDTO: CourseDTO): CourseDTO {
        val existingCourse = courseRepository.findById(courseId)
            .orElseThrow { CourseNotFoundException("No Course Found for the passed in Id $courseId") }

        existingCourse.name = courseDTO.name
        existingCourse.category = courseDTO.category

        val savedCourse = courseRepository.save(existingCourse)
        return courseEntityToDtoConverter.convert(savedCourse)
    }


    fun deleteCourse(courseId: Int) {
        val existingCourse = courseRepository.findById(courseId)
            .orElseThrow() { CourseNotFoundException("No Course Found for the Id $courseId") }
        courseRepository.delete(existingCourse)

    }
}
