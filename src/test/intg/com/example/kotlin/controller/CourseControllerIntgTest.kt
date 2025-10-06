package com.example.kotlin.controller

import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.entity.Course
import com.example.kotlin.repository.CourseRepository
import com.example.kotlin.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        val courses = courseEntityList()
        courseRepository.saveAll(courses)
    }

    @Test
    fun addCourse() {
        val  courseDTO = CourseDTO(null, "Build Restful APIs using SpringBoot and Kotlin","Dilip Sundarraj")

        val savedCourseDTO = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedCourseDTO!!.id != null
        }
    }

    @Test
    fun retrieveAllCourses() {
        val courseDTOs = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody
        println("courseDTOs : $courseDTOs")
        assertEquals(3, courseDTOs!!.size)
    }

    @Test
    fun updateCourses() {
        val course = Course(null,
            "Build RestFul APis using SpringBoot and Kotlin", "Development")
        courseRepository.save(course)
        val updateCourseDTO = CourseDTO(null,
            "Build RestFul APis using SpringBoot and Kotlin1", "Development")

        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{courseId}", course.id)
            .bodyValue(updateCourseDTO)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals("Build RestFul APis using SpringBoot and Kotlin1", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {

//        val instructor = instructorRepository.findInstructorByName("Dilip Sundarraj")
        val courseEntity = Course(null,
            "Build RestFul APis using SpringBoot and Kotlin", "Development")

        courseRepository.save(courseEntity)
        val updatedCourse = webTestClient
            .delete()
            .uri("/v1/courses/{courseId}", courseEntity.id)
            .exchange()
            .expectStatus().isNoContent

    }
}
