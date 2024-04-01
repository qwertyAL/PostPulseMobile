package com.example.data.api

import android.util.JsonReader
import com.example.domain.model.ChannelModel
import com.example.domain.model.PublicationModel
import org.json.JSONObject
import org.json.JSONStringer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
        PublicationModel(
            id = 0,
            title = "Церемония для родителей",
            text = "Приглашайте родителей на церемонию закрытия \uD83E\uDD17\n" +
                    "\n" +
                    "Церемония пройдет 4 апреля в Центре Культур НИУ ВШЭ (ул. Покровский бульвар, 11с6)\n" +
                    "\n" +
                    "Тайминг мероприятия: \n" +
                    "11:00—14:00 — фестивальная часть\n" +
                    "14:00—16:00 — награждение\n" +
                    "\n" +
                    "Чтобы родители смогли попасть на церемонию, им нужно заполнить форму (https://polls.tinkoff.ru/s/clufhdx5l00ck01kfhjop2g5k) до 1 апреля 12:00.",
            time = 1711900620
        ),
        PublicationModel(
            id = 0,
            title = "Церемония для родителей",
            text = "Приглашайте родителей на церемонию закрытия \uD83E\uDD17\n" +
                    "\n" +
                    "Церемония пройдет 4 апреля в Центре Культур НИУ ВШЭ (ул. Покровский бульвар, 11с6)\n" +
                    "\n" +
                    "Тайминг мероприятия: \n" +
                    "11:00—14:00 — фестивальная часть\n" +
                    "14:00—16:00 — награждение\n" +
                    "\n" +
                    "Чтобы родители смогли попасть на церемонию, им нужно заполнить форму (https://polls.tinkoff.ru/s/clufhdx5l00ck01kfhjop2g5k) до 1 апреля 12:00.",
            time = 1711814220
        ),
        PublicationModel(
            id = 0,
            title = "Церемония для родителей",
            text = "Приглашайте родителей на церемонию закрытия \uD83E\uDD17\n" +
                    "\n" +
                    "Церемония пройдет 4 апреля в Центре Культур НИУ ВШЭ (ул. Покровский бульвар, 11с6)\n" +
                    "\n" +
                    "Тайминг мероприятия: \n" +
                    "11:00—14:00 — фестивальная часть\n" +
                    "14:00—16:00 — награждение\n" +
                    "\n" +
                    "Чтобы родители смогли попасть на церемонию, им нужно заполнить форму (https://polls.tinkoff.ru/s/clufhdx5l00ck01kfhjop2g5k) до 1 апреля 12:00.",
            time = null
        )
    )

    suspend fun getListAllPublication(): List<PublicationModel> {
        return allPublication
    }

    suspend fun getPublicationById(id: Int): PublicationModel = allPublication[id]

    suspend fun getListAllDraftPublication(): List<PublicationModel> = listOf(allPublication[2])

    suspend fun getListAllSendPublication(): List<PublicationModel> = listOf(allPublication[1])

    suspend fun getListAllSchedulePublication(): List<PublicationModel> = listOf(allPublication[0])

    fun getListChannels(): List<ChannelModel> {
        val json = JSONObject("{\n" +
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
                "}")

        val results = json.getJSONArray("results")
        val resultList: MutableList<ChannelModel> = mutableListOf()

        for(i in 0..<results.length()) {
            resultList.add(
                ChannelModel(
                    id = results.getJSONObject(i).getInt("id"),
                    name = results.getJSONObject(i).getString("name")
                ))
        }

        return resultList
    }

}