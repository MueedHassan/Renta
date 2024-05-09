package com.example.home.Recommendation.ui.remote.function

import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.Recommendation.ui.remote.data.TenantPostResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import java.net.InetSocketAddress
import java.net.Proxy

interface PostService {
     suspend fun createPost(
        postRequest: String,
        cityName: String,
        cityRange: String
    ):  List<PostResponse>?
    suspend fun createTenantPost(
        loc1:String,
        loc2:String,
        loc3:String,
        range:String
    ):  List<TenantPostResponse>?
    companion object {
        fun create(): PostService {
            return PostServiceImpl(
                client = HttpClient(Android) {
                    engine {
                        // this: AndroidEngineConfig
                        connectTimeout = 10000
                        socketTimeout = 10000
//                        proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("dominant-modest-fish.ngrok-free.app", 80))
                    }
                    followRedirects = true
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation){
                        json(Json {  prettyPrint = true
                            isLenient = true})

                    }


                }
            )
        }
    }

}

class PostServiceImpl(private val client: HttpClient) : PostService {
    @OptIn(InternalAPI::class)
    override suspend fun createPost(
        postRequest: String,
        cityName: String,
        cityRange: String
    ): List<PostResponse>? {
        val newresponse: List<PostResponse>? = null
        return try {
            println("heeheh createpost")
                val response =
                    client.post(
                        "https://dominant-modest-fish.ngrok-free.app/Hotel_recommendation?Name=$cityName&Location=$postRequest&Price=$cityRange"
                    ){
                        contentType(ContentType.Application.Json)

                    }

              println("hehe post status ${response.body<String>()}")
                if (response.status.isSuccess()) {
                    val postResponse = response.body<String>()
                    val r=Json.decodeFromString<List<PostResponse>?>(postResponse.toString())
                    println("hehe is success { $r }")
                        // Return the received list of PostResponse
                    return r
                } else {
                    println("hehe Unsuccesfull")
                    null // Return null if the response status is not successful
                }

        }
        catch(e: RedirectResponseException) {
            // 3xx - responses
            println("heheh Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("heheh Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println(" hehe Error: ${e.response.status.description}")
            println(" hehe Error: ${e.response.body<List<PostResponse>?>()}")
            null
        } catch(e: Exception) {
            println("hehe Error: ${e.message}")
            null
        }



    }

    override suspend fun createTenantPost(
        loc1: String,
        loc2: String,
        loc3: String,
        range: String
    ): List<TenantPostResponse>? {
        val newresponse: List<TenantPostResponse>? = null
        return try {
            println("heeheh createpost")
            val response =
                client.post(
                    "http://ec2-44-222-205-3.compute-1.amazonaws.com/House_Recommendation?Preferred_Location_1=$loc1&Preferred_Location_2=$loc2&Preferred_Location_3=$loc3&Price_in_k=$range"){
                    contentType(ContentType.Application.Json)

                }

            println("hehe post status ${response.body<String>()}")
            if (response.status.isSuccess()) {
                val postResponse = response.body<String>()
                val r=Json.decodeFromString<List<TenantPostResponse>?>(postResponse.toString())
                println("hehe is success { $r }")
                // Return the received list of PostResponse
                return r
            } else {
                println("hehe Unsuccesfull")
                null // Return null if the response status is not successful
            }

        }
        catch(e: RedirectResponseException) {
            // 3xx - responses
            println("heheh Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("heheh Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println(" hehe Error: ${e.response.status.description}")
            println(" hehe Error: ${e.response.body<List<PostResponse>?>()}")
            null
        } catch(e: Exception) {
            println("hehe Error: ${e.message}")
            null
        }


    }
}
