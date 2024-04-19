package com.example.home.chatbot.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.home.R
import com.example.home.chatbot.chat.Conversation
import com.example.home.chatbot.chat.Message
import com.example.home.chatbot.chat.MessageStatus
import kotlinx.coroutines.launch


data class ChatScreenUiHandlers(
    val onSendMessage: (String) -> Unit = {},
    val onResendMessage: (Message) -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    uiHandlers: ChatScreenUiHandlers = ChatScreenUiHandlers(),
    conversation: LiveData<Conversation>,
    isSendingMessage: LiveData<Boolean>
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var inputValue by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val conversationState by conversation.observeAsState()
    val isSendingMessageState by isSendingMessage.observeAsState()

    fun sendMessage() {
        uiHandlers.onSendMessage(inputValue)
        inputValue = ""
        coroutineScope.launch {
            listState.animateScrollToItem(conversationState?.list?.size ?: 0)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TopAppBar(
            title = { Text(
                text = "Your AI Agent",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(vertical = 10.dp)

            )},
        )}

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
                .padding(vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                conversationState?.let {
                    MessageList(
                        messagesList = it.list,
                        listState = listState,
                        onResendMessage = uiHandlers.onResendMessage
                    )
                }
            }
            Row {
                TextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions {
                        sendMessage()
                    },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
                HorizontalSpacer(8.dp)
                Button(
                    modifier = Modifier.height(56.dp),
                    onClick = { sendMessage() },
                    enabled = inputValue.isNotBlank() && isSendingMessageState != true,
                ) {
                    if (isSendingMessageState == true) {
                        Icon(
                            imageVector = Icons.Default.Sync,
                            contentDescription = "Sending"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send"
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun MessageList(
    messagesList: List<Message>,
    listState: LazyListState,
    onResendMessage: (Message) -> Unit
) {
    LazyColumn(
        state = listState
    ) {
        items(messagesList.size) { message ->
            Row {
                if (messagesList[message].isFromUser) {
                    HorizontalSpacer(width = 16.dp)
                    Box(
                        modifier = Modifier.weight(weight = 1f)
                    )
                }
                messagesList[message].text?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        textAlign = if (messagesList[message].isFromUser) { TextAlign.End } else { TextAlign.Start },
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (messagesList[message].messageStatus == MessageStatus.Error) {
                                    MaterialTheme.colorScheme.errorContainer
                                } else {
                                    if (messagesList[message].isFromUser) {
                                        MaterialTheme.colorScheme.secondaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.primaryContainer
                                    }
                                }
                            )
                            .clickable(enabled = messagesList[message].messageStatus == MessageStatus.Error) {
                                onResendMessage(messagesList[message])
                            }
                            .padding(all = 8.dp)

                    )
                }
                if (!messagesList[message].isFromUser) {
                    HorizontalSpacer(width = 16.dp)
                    Box(
                        modifier = Modifier.weight(weight = 1f)
                    )
                }
            }
            if (messagesList[message].messageStatus == MessageStatus.Sending) {
                Text(
                    text = stringResource(R.string.chat_message_loading),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalSpacer(width = 32.dp)
            }
            if (messagesList[message].messageStatus == MessageStatus.Error) {
                Row(
                    modifier = Modifier
                        .clickable {
                            onResendMessage(messagesList[message])
                        }
                ) {
                    Box(
                        modifier = Modifier.weight(weight = 1f)
                    )
                    Text(
                        text = stringResource(R.string.chat_message_error),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            VerticalSpacer(height = 8.dp)
        }
    }
}

@Composable
fun VerticalSpacer(height: Dp) {
Spacer(modifier = Modifier.height(height))}

@Composable
fun HorizontalSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))}

