package com.example.data.api

import android.util.Log
import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel
import com.example.domain.model.UserModel
import com.google.gson.JsonElement
import io.ktor.client.call.body
import io.ktor.client.request.cookie
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.setCookie

val channels_mock = "{\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"PROD'финал\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 1,\n" +
        "      \"name\": \"PROD\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"prod_hse\"\n" +
        "    }\n" +
        "  ]\n" +
        "}"

class Api {

    private val allPublication: List<PublicationModel> = listOf(
//        PublicationModel(
//            id = 0,
//            title = "Церемония для родителей",
//            text = "Приглашайте родителей на церемонию закрытия \uD83E\uDD17\n" +
//                    "\n" +
//                    "Церемония пройдет 4 апреля в Центре Культур НИУ ВШЭ (ул. Покровский бульвар, 11с6)\n" +
//                    "\n" +
//                    "Тайминг мероприятия: \n" +
//                    "11:00—14:00 — фестивальная часть\n" +
//                    "14:00—16:00 — награждение\n" +
//                    "\n" +
//                    "Чтобы родители смогли попасть на церемонию, им нужно заполнить форму (https://polls.tinkoff.ru/s/clufhdx5l00ck01kfhjop2g5k) до 1 апреля 12:00.",
//            time = 1711900620,
//            channelId = 1
//        ),
//        PublicationModel(
//            id = 0,
//            title = "Церемония для родителей",
//            text = "Приглашайте родителей на церемонию закрытия \uD83E\uDD17\n" +
//                    "\n" +
//                    "Церемония пройдет 4 апреля в Центре Культур НИУ ВШЭ (ул. Покровский бульвар, 11с6)\n" +
//                    "\n" +
//                    "Тайминг мероприятия: \n" +
//                    "11:00—14:00 — фестивальная часть\n" +
//                    "14:00—16:00 — награждение\n" +
//                    "\n" +
//                    "Чтобы родители смогли попасть на церемонию, им нужно заполнить форму (https://polls.tinkoff.ru/s/clufhdx5l00ck01kfhjop2g5k) до 1 апреля 12:00.",
//            time = 1711814220,
//            channelId = 1
//        ),
//        PublicationModel(
//            id = 0,
//            title = "Церемония для родителей",
//            text = "Приглашайте родителей на церемонию закрытия \uD83E\uDD17\n" +
//                    "\n" +
//                    "Церемония пройдет 4 апреля в Центре Культур НИУ ВШЭ (ул. Покровский бульвар, 11с6)\n" +
//                    "\n" +
//                    "Тайминг мероприятия: \n" +
//                    "11:00—14:00 — фестивальная часть\n" +
//                    "14:00—16:00 — награждение\n" +
//                    "\n" +
//                    "Чтобы родители смогли попасть на церемонию, им нужно заполнить форму (https://polls.tinkoff.ru/s/clufhdx5l00ck01kfhjop2g5k) до 1 апреля 12:00.",
//            time = null,
//            channelId = 1
//        )
    )

    suspend fun getListAllPublication(): List<PublicationModel> {
        return allPublication
    }

    suspend fun getPublicationById(id: Int): PublicationModel = allPublication[id]

    suspend fun sendMobileToken(token: String, cookie: String) {
        try {
            val response = ApiConstants.httpClient.post(ApiConstants.SENT_MOBILE_TOKEN_URL) {
                headers.append(HttpHeaders.Cookie, "session=$cookie")
                setBody("{ \"token\": \"$token\" }")
//                parameter("token", token)
            }
        } catch (e: Exception) {
            Log.i("SEND_TOKEN", e.toString())
        }

    }

    suspend fun updatePost(cookie: String, id: Int, channelId: Int, name: String, text: String, comment: String, scheduledAt: String?) {
        try {
            val response = ApiConstants.httpClient.put(ApiConstants.UPDATE_POST_URL) {
                headers.append(HttpHeaders.Cookie, "session:$cookie")
                setBody("{ \"id\": \"$id\", \"channelId\": \"$channelId\", \"name\": \"$name\", \"text\": \"$text\", \"comment\": \"$comment\" }")
            }
        } catch (e: Exception) {
            Log.i("TEST_UPDATE", e.toString())
        }
    }

    suspend fun getListAllDraftPublication(cookie: String, channelId: Int?): List<PublicationModel> {

        try {
            val response = ApiConstants.httpClient.get(ApiConstants.LIST_DRAFTS_URL) {
                headers.append(HttpHeaders.Cookie, "session=$cookie;")
                parameter("channelId", channelId)
            }
            Log.i("TEST_PUBLICATIONS", channelId.toString())

            if(response.status.value in (200..299)) {
                val data = response.body<JsonElement>()
                Log.i("TEST_DRAFTS", data.toString())
                return buildList {
                    for(i in 0..<data.asJsonArray.size()) {
                        add(PublicationModel(
                            id = data.asJsonArray.get(i).asJsonObject.get("id").toString().toInt(),
                            channelId = data.asJsonArray.get(i).asJsonObject.get("channelId").toString().toInt(),
                            text = data.asJsonArray.get(i).asJsonObject.get("text").toString().replace("\"", ""),
                            comment = data.asJsonArray.get(i).asJsonObject.get("comment").toString().replace("\"", ""),
                            scheduledAt = data.asJsonArray.get(i).asJsonObject.get("scheduledAt").toString().replace("\"", ""),
                            updatedAt = data.asJsonArray.get(i).asJsonObject.get("updatedAt").toString().replace("\"", ""),
                            name = data.asJsonArray.get(i).asJsonObject.get("name").toString().replace("\"", "")
                        ))
                    }
                }
            }
        } catch (e: Exception) {
            Log.i("TEST_DRAFTS", e.toString())
        }

        return emptyList()
    }

    suspend fun getListAllSendPublication(cookie: String, channelId: Int?): List<PublicationModel> {

        try {
            val response = ApiConstants.httpClient.get(ApiConstants.LIST_SEND_URL) {
                headers.append(HttpHeaders.Cookie, "session=$cookie;")
                parameter("channelId", channelId)
            }

            if(response.status.value in (200..299)) {
                val data = response.body<JsonElement>()
                Log.i("TEST_DRAFTS", data.toString())
                return buildList {
                    for(i in 0..<data.asJsonArray.size()) {
                        add(PublicationModel(
                            id = data.asJsonArray.get(i).asJsonObject.get("id").toString().toInt(),
                            channelId = data.asJsonArray.get(i).asJsonObject.get("channelId").toString().toInt(),
                            text = data.asJsonArray.get(i).asJsonObject.get("text").toString().replace("\"", ""),
                            comment = data.asJsonArray.get(i).asJsonObject.get("comment").toString().replace("\"", ""),
                            scheduledAt = data.asJsonArray.get(i).asJsonObject.get("scheduledAt").toString().replace("\"", ""),
                            updatedAt = data.asJsonArray.get(i).asJsonObject.get("updatedAt").toString().replace("\"", ""),
                            name = data.asJsonArray.get(i).asJsonObject.get("name").toString().replace("\"", "")
                        ))
                    }
                }
            }
        } catch (e: Exception) {
            Log.i("TEST_DRAFTS", e.toString())
        }

        return emptyList()
    }

    suspend fun getListAllSchedulePublication(cookie: String, channelId: Int?): List<PublicationModel> {

        try {
            val response = ApiConstants.httpClient.get(ApiConstants.LIST_SCHEDULE_URL) {
                headers.append(HttpHeaders.Cookie, "session=$cookie;")
                parameter("channelId", channelId)
            }

            Log.i("TEST_CHANNELS", channelId.toString())
            if(response.status.value in (200..299)) {
                val data = response.body<JsonElement>()
                Log.i("TEST_SCHEDULE", data.toString())
                return buildList {
                    for(i in 0..<data.asJsonArray.size()) {
                        add(PublicationModel(
                            id = data.asJsonArray.get(i).asJsonObject.get("id").toString().toInt(),
                            channelId = data.asJsonArray.get(i).asJsonObject.get("channelId").toString().toInt(),
                            text = data.asJsonArray.get(i).asJsonObject.get("text").toString().replace("\"", ""),
                            comment = data.asJsonArray.get(i).asJsonObject.get("comment").toString().replace("\"", ""),
                            scheduledAt = data.asJsonArray.get(i).asJsonObject.get("scheduledAt").toString().replace("\"", ""),
                            updatedAt = data.asJsonArray.get(i).asJsonObject.get("updatedAt").toString().replace("\"", ""),
                            name = data.asJsonArray.get(i).asJsonObject.get("name").toString().replace("\"", "")
                        ))
                    }
                }
            }
        } catch (e: Exception) {
            Log.i("TEST_DRAFTS", e.toString())
        }

        return emptyList()
    }

    suspend fun getListChannels(cookie: String): List<ChannelModel> {
        try {
            val response = ApiConstants.httpClient.get(ApiConstants.LIST_CHANNELS_URL) {
//                headers.append(HttpHeaders.Cookie, "session=$cookie;")
                cookie(name = "session", cookie)
            }
            Log.i("TEST_CHANNELS", cookie)
            if(response.status.value in (200..299)) {
                val data = response.body<JsonElement>()
                Log.i("TEST_CHANNELS", data.toString())
                return buildList {
                    for(i in 0..<data.asJsonArray.size()) {
                        add(ChannelModel(
                            id = data.asJsonArray.get(i).asJsonObject.get("id").toString().toInt(),
                            name = data.asJsonArray.get(i).asJsonObject.get("name").toString().replace("\"", "")
                        ))
                    }
                }
            }
            else {
                Log.i("TEST_CHANNELS", response.status.value.toString())
            }
            return emptyList()
        } catch (e: Exception) {
            return emptyList()
        }
    }

    suspend fun loginUser(authData: String): UserModel? {
        try {
            val response = ApiConstants.httpClient.request {
                url(ApiConstants.LOGIN_URL)
                method = HttpMethod.Post
                contentType(ContentType.Application.Json)
                setBody(authData)
            }

            if (response.status.value in (200..299)) {
                // ТУТ ПОПРАВИТЬ НАДА ПАРСИНГ
                val id = response.toString()
                val cookieStore = response.headers
                Log.i("TEST_HEADER", cookieStore.toString())
                return UserModel(
                    id = id,
                    cookie = response.setCookie()[0].value
                )
            }
            return null
        } catch (e: Exception) {
            Log.i("TEST_HEADER", e.toString())
            return null
        }
    }

}