package co.uk.mcb.websocket.client;

import static org.junit.Assert.*

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test

class SwarmApiTest {

	@Test
	public void test() {
		WebSocketClient client = new WSClient("ws://swarm.betconstruct.com:8086")
		client.connectBlocking()
		client.send '{"command":"get","params":{"source":"betting","what":{"sport":[]},"where":{},"subscribe":false }}'
	}

	private static class WSClient extends WebSocketClient {
		WSClient(String url) {
			super(new URI(url))
		}

		@Override
		public void onOpen(ServerHandshake handshakedata) {
			send '{"command":"request_session","params":{"language":"arm","site_id":702}}'
		}

		@Override
		public void onMessage(String message) {
			println message
		}

		@Override
		public void onClose(int code, String reason, boolean remote) {

		}

		@Override
		public void onError(Exception ex) {

		}
	}
}
