package co.uk.mcb.websocket.client;

import org.springframework.context.annotation.Scope
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Scope("singleton")
@RequestMapping("/swarm")
class WebSocketAdapterRestController {
	private Map<String, WebSocketAdapterClient> sessionToAdapter = [:]

	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	@ResponseBody
	String connect(@RequestParam("sessionId") String sessionId, @RequestParam("wsurl") String wsurl, @RequestParam("callbackurl") String callbackurl, @RequestParam("message") String message) {
		if (!sessionToAdapter.containsKey(sessionId)) {
			def client = new WebSocketAdapterClient(wsurl, callbackurl)
			client.onOpenMessage = message
			client.connectBlocking()
			sessionToAdapter.put(sessionId, client)
		}
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	String send(@RequestParam("sessionId") String sessionId, @RequestParam("message") String message) {
		def client = sessionToAdapter[sessionId]
		if (client) {
			client.send(message)
		}
		else {
			println "client not connected: $client"
		}
	}
	
	@RequestMapping(value = "/disconnect", method = RequestMethod.POST)
	@ResponseBody
	String disconnect(@RequestParam("sessionId") String sessionId) {
		def client = sessionToAdapter[sessionId]
		client?.close()
	}
	
}
