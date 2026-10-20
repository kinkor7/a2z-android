package com.a2z.international.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

data class ChatMessage(val text: String, val isUser: Boolean)

@Composable
fun CareerAssistantScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var messages by remember { mutableStateOf(listOf(ChatMessage("Hi 👋 I’m the A2Z Career Assistant. Let's start with your name!", false))) }
    var currentStep by remember { mutableStateOf(0) }
    var inputText by remember { mutableStateOf("") }

    val steps = listOf("Name", "Country", "Language", "Job", "Experience")
    val userData = remember { mutableStateMapOf<String, String>() }

    fun sendMessage() {
        if (inputText.isBlank()) return

        val userMsg = inputText
        messages = messages + ChatMessage(userMsg, true)

        val step = steps[currentStep]
        userData[step] = userMsg

        inputText = ""
        currentStep++

        if (currentStep < steps.size) {
            val nextStep = steps[currentStep]
            messages = messages + ChatMessage("Great! Now, please tell me your $nextStep:", false)
        } else {
            messages = messages + ChatMessage("Thank you! I have collected your details. Click below to connect with our team on WhatsApp.", false)
        }
    }

    fun openWhatsApp() {
        val text = "I would like to enquire about employment opportunities.\\n\\n" +
                "Name: ${userData["Name"]}\\n" +
                "Country: ${userData["Country"]}\\n" +
                "Language: ${userData["Language"]}\\n" +
                "Job: ${userData["Job"]}\\n" +
                "Experience: ${userData["Experience"]}\\n\\n" +
                "Source: A2Z App Assistant"

        val whatsappUrl = "https://wa.me/447426410645?text=${Uri.encode(text)}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))
        context.startActivity(intent)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Career Assistant", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Back") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { msg ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = if (msg.isUser) Alignment.CenterEnd else Alignment.CenterStart
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (msg.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text(
                                text = msg.text,
                                modifier = Modifier.padding(8.dp),
                                color = if (msg.isUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            if (currentStep < steps.size) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Type here...") }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { sendMessage() }) {
                        Text("Send")
                    }
                }
            } else {
                Button(
                    onClick = { openWhatsApp() },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Connect on WhatsApp", color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    }
}
