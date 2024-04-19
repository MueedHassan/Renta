package com.example.home.chatbot.chat.ui

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
@Destination
@Composable
fun ChatActivity(){
    val viewModel: ChatViewModel = koinViewModel<ChatViewModel>()

                ChatScreen(
                    uiHandlers = ChatScreenUiHandlers(
                        onSendMessage = viewModel::sendMessage,
                        onResendMessage = viewModel::resendMessage
                    ),
                    conversation = viewModel.conversation,
                    isSendingMessage = viewModel.isSendingMessage
                )



}