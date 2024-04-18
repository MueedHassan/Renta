package com.example.home.chatbot.chat.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class ChatActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by stateViewModel(
        state = { intent?.extras ?: Bundle() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatGptBotAppTheme {
                ChatScreen(
                    uiHandlers = ChatScreenUiHandlers(
                        onSendMessage = viewModel::sendMessage,
                        onResendMessage = viewModel::resendMessage
                    ),
                    conversation = viewModel.conversation,
                    isSendingMessage = viewModel.isSendingMessage
                )
            }
        }
    }
}