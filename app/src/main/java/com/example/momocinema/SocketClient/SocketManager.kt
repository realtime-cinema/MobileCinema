import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import java.net.URI
import java.net.URISyntaxException

object SocketManager {
    private lateinit var socket: Socket
    @Synchronized
    fun setSocket(){
        try {
            val uri:URI = URI("http://10.0.2.2:9998")
            socket = IO.socket(uri)
        } catch (e: URISyntaxException) {
            Log.e("SOCKET_ERROR", e.toString())
        }
    }
    @Synchronized
    fun getSocket():Socket{
        return socket
    }

}