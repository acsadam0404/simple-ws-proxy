package co.uk.mcb.websocket.client;

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class WebSocketAdapterClient extends WebSocketClient {
	String onOpenMessage
	String callbackUrl

	WebSocketAdapterClient(String wsurl, String callbackUrl) {
		super(new URI(wsurl))
		this.callbackUrl = callbackUrl
		println "new websocketadapter (callbackurl=${callbackUrl}), wsurl=${wsurl}"
	}

	@Override
	void onOpen(ServerHandshake handshakedata) {
		if (onOpenMessage) {
			println 'sending onopenmessage: ' + onOpenMessage
			send onOpenMessage
		}
	}

	@Override
	void onMessage(String message) {
		println message
		println new URL(callbackUrl+ URLEncoder.encode('?message=' + message, 'UTF-8')).text
	}

	@Override
	void onClose(int code, String reason, boolean remote) {
		println reason
	}

	@Override
	void onError(Exception ex) {
		ex.printStackTrace();
	}
}
