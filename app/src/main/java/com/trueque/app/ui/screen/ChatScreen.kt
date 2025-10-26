package com.trueque.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.trueque.app.ui.viewmodel.SimpleChatViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    userId: String,
    onNavigateBack: () -> Unit
) {
    val viewModel = remember { SimpleChatViewModel() }
    var messageText by remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    
    // Demo messages
    val demoMessages = listOf(
        DemoMessage(
            id = "msg1",
            senderId = userId,
            receiverId = "currentUser",
            message = "¡Hola! Me interesa tu servicio de diseño de logo",
            timestamp = Date(),
            isRead = true
        ),
        DemoMessage(
            id = "msg2",
            senderId = "currentUser",
            receiverId = userId,
            message = "¡Hola! Perfecto, ¿podrías contarme más sobre tu proyecto?",
            timestamp = Date(),
            isRead = true
        ),
        DemoMessage(
            id = "msg3",
            senderId = userId,
            receiverId = "currentUser",
            message = "Necesito un logo para mi nueva empresa de tecnología. ¿Cuánto tiempo tomaría?",
            timestamp = Date(),
            isRead = true
        )
    )
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = { 
                Column {
                    Text(
                        text = "USUARIO",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "En línea",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            },
            actions = {
                IconButton(onClick = { /* Video call */ }) {
                    Icon(Icons.Default.VideoCall, contentDescription = "Video llamada")
                }
                IconButton(onClick = { /* More options */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Más opciones")
                }
            }
        )
        
        // Messages Placeholder
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Chat",
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Aquí se mostrarán los mensajes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Text(
                    text = "Los mensajes aparecerán en esta área cuando inicies una conversación",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // Message Input
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Attach file */ }) {
                    Icon(Icons.Default.AttachFile, contentDescription = "Adjuntar")
                }
                
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    placeholder = { Text("Escribe un mensaje...") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (messageText.isNotEmpty()) {
                                    // Send message
                                    messageText = ""
                                }
                            }
                        ) {
                            Icon(Icons.Default.Send, contentDescription = "Enviar")
                        }
                    }
                )
                
                IconButton(onClick = { /* Emoji */ }) {
                    Icon(Icons.Default.EmojiEmotions, contentDescription = "Emoji")
                }
            }
        }
    }
}

@Composable
fun MessageBubble(
    message: DemoMessage,
    isFromCurrentUser: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromCurrentUser) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        if (!isFromCurrentUser) {
            // Avatar for received messages
            Card(
                modifier = Modifier.size(32.dp),
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (isFromCurrentUser) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            )
        ) {
            Text(
                text = message.message,
                modifier = Modifier.padding(12.dp),
                color = if (isFromCurrentUser) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
        
        if (isFromCurrentUser) {
            Spacer(modifier = Modifier.width(8.dp))
            
            // Avatar for sent messages
            Card(
                modifier = Modifier.size(32.dp),
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

data class DemoMessage(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val message: String,
    val timestamp: Date,
    val isRead: Boolean
)
