package com.example.miprimerproyectodeapp.data

import androidx.compose.ui.graphics.Color
import com.example.miprimerproyectodeapp.model.*

private val trabajo = Category(1, "Trabajo")
private val estudio = Category(2, "Estudio")
private val personal = Category(3, "Personal")

// SET: no admite elementos repetidos
val categories: Set<Category> = setOf(trabajo, estudio, personal, trabajo)

// MAP: categoría -> color
val categoryColors: Map<String, Color> = mapOf(
    "Trabajo" to Color(0xFF1565C0),
    "Estudio" to Color(0xFF2E7D32),
    "Personal" to Color(0xFFEF6C00)
)

// LIST: recordatorios simulados
val sampleReminders: List<Reminder> = listOf(
    Reminder(1, "Entregar informe", "Informe semanal", trabajo, Priority.ALTA, ReminderStatus.Pending, "30/10/2026"),
    Reminder(2, "Estudiar para el examen", null, estudio, Priority.ALTA, ReminderStatus.Pending),
    Reminder(3, "Comprar pan", null, personal, Priority.BAJA, ReminderStatus.Completed),
    Reminder(4, "Reunión con el equipo", "Revisar avances", trabajo, Priority.MEDIA, ReminderStatus.Postponed("02/11/2026")),
    Reminder(5, "Llamar al dentista", null, personal, Priority.MEDIA, ReminderStatus.Pending, "05/11/2026")
)

// Filtros por estado y prioridad
val pendingReminders = sampleReminders.filter { it.status is ReminderStatus.Pending }
val highPriorityReminders = sampleReminders.filter { it.priority == Priority.ALTA }