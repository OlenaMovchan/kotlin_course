package com.example.kotlin.service

import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.entity.Course
import com.example.kotlin.exception.CourseNotFoundException
import com.example.kotlin.exception.InstructorNotValidException
import com.example.kotlin.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val instructorService: InstructorService
) {

    companion object : KLogging()

    fun addCourse(courseDTO: CourseDTO) : CourseDTO {
        val instructorOptional = instructorService.findInstructorById(courseDTO.instructorId!!)
        if (!instructorOptional.isPresent) {
            throw InstructorNotValidException("Instructor Not Valid for the Id: ${courseDTO.instructorId}")
        }
        val courseEntity = courseDTO.let {
            Course(null, it.name, it.category, instructorOptional.get())
        }
        courseRepository.save(courseEntity)

        logger.info("Saved course is : $courseEntity")

        return courseEntity.let {
            CourseDTO(it.id, it.name, it.category, it.instructor!!.id)
        }
   }

    fun retrieveAllCourses(courseName: String?): List<CourseDTO> {
        val  courses = courseName?.let{
            courseRepository.findCoursesByName(courseName)
        }?:courseRepository.findAll()
        return courses
            .map {
                CourseDTO(it.id, it.name, it.category)
            }
    }

    fun updateCourse(courseId: Int, courseDTO: CourseDTO): CourseDTO {
        val existingCourse = courseRepository.findById(courseId)
        return if (existingCourse.isPresent) {
            existingCourse.get()
                .let {
                    it.category = courseDTO.category
                    it.name = courseDTO.name
                    courseRepository.save(it)
                    //  CourseDTO(it.id, it.name, it.category)
                    CourseDTO(it.id, it.name, it.category)
                }
        } else {
            throw CourseNotFoundException("No Course Found for the passed in Id $courseId")
        }
    }

    fun deleteCourse(courseId: Int) {
        val existingCourse = courseRepository.findById(courseId)
        if (existingCourse.isPresent) {
            existingCourse.get()
                .let {
                    courseRepository.deleteById(courseId)
                }
        } else {
            throw CourseNotFoundException("No Course Found for the passed in Id $courseId")
        }


    }
}
