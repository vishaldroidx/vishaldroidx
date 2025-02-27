package com.techwarezen.tradelearn.data.model

/**
 * Data class representing a student
 *
 * @param id Unique identifier for the student
 * @param name Student's full name
 * @param email Student's email address
 * @param className Class or group the student belongs to
 * @param virtualBalance Virtual balance assigned to the student
 * @param isActive Whether the student account is active
 */
data class Student(
    val id: String,
    val name: String,
    val email: String,
    val className: String,
    val virtualBalance: Double,
    val isActive: Boolean
)