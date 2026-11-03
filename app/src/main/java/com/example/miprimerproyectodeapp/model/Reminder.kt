package com.example.miprimerproyectodeapp.model

enum class Priority { BAJA, MEDIA, ALTA }

data class Category(val id: Int, val name: String)

sealed class ReminderStatus {
    data object Pending : ReminderStatus()
    data object Completed : ReminderStatus()
    data class Postponed(val newDate: String) : ReminderStatus()
}

data class Reminder(
    val id: Int,
    val title: String,
    val description: String? = null,
    val category: Category,
    val priority: Priority,
    val status: ReminderStatus = ReminderStatus.Pending,
    val dueDate: String? = null
)

sealed class ValidationResult {
    data object Valid : ValidationResult()
    data class Invalid(val message: String) : ValidationResult()
}

fun validateReminder(title: String, dueDate: String?): ValidationResult = when {
    title.isBlank() -> ValidationResult.Invalid("El título no puede estar vacío")
    title.length < 3 -> ValidationResult.Invalid("El título debe tener al menos 3 caracteres")
    dueDate != null && dueDate.isBlank() -> ValidationResult.Invalid("La fecha no puede ser un texto vacío")
    else -> ValidationResult.Valid
}