package co.uk.mcb.websocket.client;

import static org.junit.Assert.*

import org.junit.Test
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

class WebSocketAdapterRestControllerTest {
	@Test
	void test() {
		def sessionId = 10
		connect(sessionId)
		query(sessionId)
	}

	def connect(sessionId) {
		RestTemplate rt = new RestTemplate()
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add('sessionId', sessionId)
		map.add('wsurl', 'ws://swarm.betconstruct.com:8086')
		map.add('callbackurl', 'http://localhost:8080/swarm/echo')
		map.add('message', '{"command":"request_session","params":{"language":"arm","site_id":702}}')
		println rt.postForObject("http://localhost:8080/swarm/connect", map, String)
	}

	def query(sessionId) {
		RestTemplate rt = new RestTemplate()
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add('sessionId', sessionId)
		map.add('message', '{"command":"get","params":{"source":"betting","what":{"sport":[]},"where":{},"subscribe":false}}')
		println rt.postForObject("http://localhost:8080/swarm/send", map, String)
	}
}
