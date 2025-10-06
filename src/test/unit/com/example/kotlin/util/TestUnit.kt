package com.example.kotlin.util

import com.example.kotlin.dto.CourseDTO
import com.example.kotlin.entity.Course

fun courseEntityList() = listOf(
    Course(null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development"),
    Course(null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development"
        ,
    ),
    Course(null,
        "Wiremock for Java Developers", "Development" ,
    )
)

fun courseDTO(
    id: Int? = null,
    name: String = "Build RestFul APis using Spring Boot and Kotlin",
    category: String = "Dilip Sundarraj",
//    instructorId: Int? = 1
) = CourseDTO(
    id,
    name,
    category,
//    instructorId
)
