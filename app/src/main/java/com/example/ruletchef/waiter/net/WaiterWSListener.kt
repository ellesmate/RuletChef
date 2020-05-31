package com.example.ruletchef.waiter.net

import android.util.Log
import com.example.ruletchef.api.RetrofitBuilder
import com.example.ruletchef.models.Order
import com.example.ruletchef.models.OrderItem
import com.example.ruletchef.models.Update
import com.example.ruletchef.waiter.viewmodels.WaiterNavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString


class WaiterWSListener(val viewModel: WaiterNavigationViewModel) : WebSocketListener() {
    class Message(val order: Order?, val delivered: OrderItem?, val payed: Order?)

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
                runBlocking(Dispatchers.Main) {
                    (viewModel.pushBack(this@apply))
                }

            } catch (e: Exception) {
                Log.d("WaiterWSListenerOrder", e.message?:"Without message")
            }
        }

        message.delivered?.apply {
            try {
                runBlocking(Dispatchers.Main) {
                    viewModel.receiveOrderItem(this@apply)
                }

            } catch (e: Exception) {
                Log.d("WaiterWSListenerOrder", e.message?:"Without message Delivered")
            }
        }

        message.payed?.apply {
            try {
                runBlocking(Dispatchers.Main) {
                    viewModel.receiveOrder(this@apply)
                }

            } catch (e: Exception) {
                Log.d("WaiterWSListenerOrder", e.message?:"Without message Payed")
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
