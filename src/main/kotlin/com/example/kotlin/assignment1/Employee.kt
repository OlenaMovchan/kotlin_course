package com.example.kotlin.assignment1

data class Employee(
    val id: Int,
    val name: String
)

fun main() {

    val employee1 = Employee(1, "Employee")
    println("Employee 1: ${employee1.id}")

    val employee2 = Employee(1, "Employee")
    println("Employee 1: ${employee2.id}")

    println("Compare the objects ${employee1 == employee2}")

    val employee3 = employee1.copy(id = 2)
    println("Using copy function (creating another object with the new id): ${employee3.id}")
}
