package com.example.ruletchef.api

import android.util.Log
import androidx.annotation.MainThread
//import com.example.ruletchef.livedata.PushbackableLiveData
import com.example.ruletchef.models.Order
import com.example.ruletchef.models.Update
import com.example.ruletchef.viewmodels.NavigationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WSListener(val viewModel: NavigationViewModel) : WebSocketListener() {
    class Message(val order: Order?, val update: Update?)

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

        val message = RetrofitBuilder.gson.fromJson(text, Message::class.java)
        Log.d("WSListener", text)
        Log.d("WSListener", message.order.toString())

        message.order?.apply {
            try {
                runBlocking(Main) {
                    (viewModel.pushBack(this@apply))
                }

            } catch (e: Exception) {
                Log.d("WSListenerOrder", e.message?:"Without message")
            }
        }

        message.update?.apply {
            try {
                runBlocking(Main) {
                    (viewModel.applyUpdate(this@apply))
                }
            } catch (e: Exception) {
                Log.d("WSListenerUpdate", e.message?:"Without message")
            }
        }
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        Log.d("WSListener", "ByteString")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d("WSListener", t.message?: "Without message")

        super.onFailure(webSocket, t, response)
    }
}