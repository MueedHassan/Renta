package com.example.home.chatbot.di

import com.example.home.chatbot.chat.ConversationRepository
import com.example.home.chatbot.chat.data.OpenAIRepository
import com.example.home.chatbot.chat.domain.ObserveMessagesUseCase
import com.example.home.chatbot.chat.domain.ResendMessageUseCase
import com.example.home.chatbot.chat.domain.SendChatRequestUseCase
import com.example.home.chatbot.chat.ui.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatModule = module {
    viewModel {
        ChatViewModel(get(), get(), get())
    }
    single { OpenAIRepository(openAI = get()) }
    single { ConversationRepository() }

    single { SendChatRequestUseCase(openAIRepository = get(), conversationRepository = get()) }
    single { ResendMessageUseCase(openAIRepository = get(), conversationRepository = get()) }
    single { ObserveMessagesUseCase(conversationRepository = get()) }
}