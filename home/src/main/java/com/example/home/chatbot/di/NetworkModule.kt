package com.example.home.chatbot.di

import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import org.koin.dsl.module

val networkModule = module {
    single { provideOpenAI() }
}

fun provideOpenAI() : OpenAI {
    val config = OpenAIConfig(
        token = "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"
    )
    return OpenAI(config)

}