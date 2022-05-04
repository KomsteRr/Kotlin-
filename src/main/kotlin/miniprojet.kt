package com.zetcode

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import com.google.gson.Gson
import jdk.jfr.DataAmount

data class BTCBean(
    var amount :Int,
    var currency :String
)

fun sendGet(url: String): String {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    return response.body()
}

class RequestUtils {
    companion object {
        fun loadBTC(): BTCBean {
            val json: String = sendGet("https://api.coinbase.com/v2/prices/BTC-USD")

            val data = Gson().fromJson(json, BTCBean::class.java)

            return data
        }
    }
}

fun main() {
    val BTC = RequestUtils.loadBTC()

    println("le BTC est a ${BTC.amount} ${BTC.currency}")
}