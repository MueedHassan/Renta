package com.example.home.chatbot.chat.domain

import com.example.home.chatbot.chat.ConversationRepository


class ObserveMessagesUseCase(
    private val conversationRepository: ConversationRepository
) {

    operator fun invoke() = conversationRepository.conversationFlow

}