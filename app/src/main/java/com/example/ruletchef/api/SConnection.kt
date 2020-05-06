package com.example.ruletchef.api

import android.os.Handler
import okhttp3.*
import java.util.concurrent.TimeUnit


class SConnection (val url: String) {
    val mClient = OkHttpClient.Builder()
        .readTimeout(3, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    enum class ConnectionStatus {
        DISCONNECTED,
        CONNECTED
    }

    interface ServerListener {
        fun onNewMessage(message : String)
        fun onStatusChange(status : ConnectionStatus)
    }

    private lateinit var mWebSocket: WebSocket
    private lateinit var mServerUrl: String
    private var mMessageHandler: Handler? = null
    private var mStatusHandler: Handler? = null
    private var mListener: ServerListener? = null


    public class SocketListener : WebSocketListener() {

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
        }
    }

    fun connect(listener: ServerListener) {
        val request = Request.Builder()
            .url(mServerUrl)
            .build();
        mWebSocket = mClient.newWebSocket(request, SocketListener())
        mListener = listener
//        mMessageHandler = object: Handler(msg -> {mListener.onNewMessage((String) msg.obj);  return true;});
//        mStatusHandler = Handler(msg -> { mListener.onStatusChange((ConnectionStatus) msg.obj);   return true;});
    }

    fun disconnect() {
        mWebSocket.cancel()
        mListener = null
        mMessageHandler?.removeCallbacksAndMessages(null);
        mStatusHandler?.removeCallbacksAndMessages(null);
    }

    fun sendMessage(message: String) {
        mWebSocket.send(message);
    }
}

//class SConnection {
//    SConnection(String url) {
//        mClient = new OkHttpClient.Builder()
//            .readTimeout(3,  TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .build();
//
//        mServerUrl = url;
//    }
//
//    public enum ConnectionStatus {
//        DISCONNECTED,
//        CONNECTED
//    }
//
//    public interface ServerListener {
//        void onNewMessage(String message);
//        void onStatusChange(ConnectionStatus status);
//    }
//
//    private WebSocket mWebSocket;
//    private OkHttpClient mClient;
//    private String mServerUrl;
//    private Handler mMessageHandler;
//    private Handler mStatusHandler;
//    private ServerListener mListener;
//
//
//    public class SocketListener extends WebSocketListener {
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            Message m = mStatusHandler.obtainMessage(0, ConnectionStatus.CONNECTED);
//            mStatusHandler.sendMessage(m);
//        }
//
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
//            Message m = mMessageHandler.obtainMessage(0, text);
//            mMessageHandler.sendMessage(m);
//        }
//
//        @Override
//        public void onClosed(WebSocket webSocket, int code, String reason) {
//            Message m = mStatusHandler.obtainMessage(0, ConnectionStatus.DISCONNECTED);
//            mStatusHandler.sendMessage(m);
//        }
//
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//            disconnect();
//        }
//    }
//
//    void connect(ServerListener listener) {
//        Request request = new Request.Builder()
//            .url(mServerUrl)
//            .build();
//        mWebSocket = mClient.newWebSocket(request, new SocketListener());
//        mListener = listener;
//        mMessageHandler = new Handler(msg -> {mListener.onNewMessage((String) msg.obj);  return true;});
//        mStatusHandler = new Handler(msg -> { mListener.onStatusChange((ConnectionStatus) msg.obj);   return true;});
//    }
//
//    void disconnect() {
//        mWebSocket.cancel();
//        mListener = null;
//        mMessageHandler.removeCallbacksAndMessages(null);
//        mStatusHandler.removeCallbacksAndMessages(null);
//    }
//
//    void sendMessage(String message) {
//        mWebSocket.send(message);
//    }
//
