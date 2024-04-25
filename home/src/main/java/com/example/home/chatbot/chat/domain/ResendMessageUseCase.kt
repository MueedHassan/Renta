package com.example.home.chatbot.chat.domain

import com.example.home.chatbot.chat.ConversationRepository
import com.example.home.chatbot.chat.Message
import com.example.home.chatbot.chat.MessageStatus
import com.example.home.chatbot.chat.data.OpenAIRepository

class ResendMessageUseCase(
    private val openAIRepository: OpenAIRepository,
    private val conversationRepository: ConversationRepository
) {

    suspend operator fun invoke(
        message: Message
    ) {
        val conversation = conversationRepository.resendMessage(message)

        try {
            val reply = openAIRepository.sendChatRequest(conversation)
            conversationRepository.setMessageStatusToSent(message.id)
            conversationRepository.addMessage(reply)
        } catch (exception: Exception) {
            conversationRepository.setMessageStatusToError(message.id)
        }
    }
}