package com.example.kotlin.repository

import com.example.kotlin.entity.Course
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface CourseRepository : CrudRepository<Course, Int> {
    fun findByNameContaining(courseName : String) : List<Course>

    @Query(value = "SELECT * FROM courses WHERE name LIKE :name", nativeQuery = true)
    fun findCoursesByName(@Param("name") name: String): List<Course>
}
