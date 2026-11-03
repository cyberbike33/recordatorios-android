package com.example.miprimerproyectodeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miprimerproyectodeapp.data.categoryColors
import com.example.miprimerproyectodeapp.data.sampleReminders
import com.example.miprimerproyectodeapp.model.*
import com.example.miprimerproyectodeapp.ui.theme.MiPrimerProyectoDeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerProyectoDeAppTheme {
                ReminderListScreen(sampleReminders)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderListScreen(reminders: List<Reminder>) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis recordatorios") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(reminders, key = { it.id }) { ReminderCard(it) }
        }
    }
}

@Composable
fun ReminderCard(reminder: Reminder) {
    val color = categoryColors[reminder.category.name] ?: Color.Gray
    val statusText = when (val s = reminder.status) {
        is ReminderStatus.Pending -> "Pendiente"
        is ReminderStatus.Completed -> "Completado"
        is ReminderStatus.Postponed -> "Pospuesto hasta ${s.newDate}"
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(reminder.title, style = MaterialTheme.typography.titleMedium)
            reminder.description?.let {
                Text(it, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(8.dp))
            Text("Categoría: ${reminder.category.name}", color = color)
            Text("Prioridad: ${reminder.priority.name}")
            Text("Estado: $statusText")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderListPreview() {
    MiPrimerProyectoDeAppTheme {
        ReminderListScreen(sampleReminders)
    }
}