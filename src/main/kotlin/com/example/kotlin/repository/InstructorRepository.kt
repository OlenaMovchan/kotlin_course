package com.example.kotlin.repository

import com.example.kotlin.entity.Instructor
import org.springframework.data.repository.CrudRepository

interface InstructorRepository : CrudRepository<Instructor, Int> {

    fun findInstructorByName(name : String) : Instructor
}
