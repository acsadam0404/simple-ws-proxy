package co.uk.mcb.rest

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import co.uk.mcb.service.ServiceLocator
import co.uk.mcb.user.User
import co.uk.mcb.user.UserService

@RestController
class EchoRestController {
	
	@RequestMapping("/echo")
	@ResponseBody
	String echo(@RequestParam("message") String message) {
		message
	}

	@RequestMapping("/ping")
	@ResponseBody
	String ping() {
		"pong"
	}
	
	@RequestMapping("/json")
	@ResponseBody
	DummyJsonWrapper json(@RequestBody DummyJsonWrapper json) {
		new DummyJsonWrapper(message: "received $json.message")
	}
	
	static class DummyJsonWrapper {
		String message
	}
	
}
