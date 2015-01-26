package co.uk.mcb.rest

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EchoRestController {
	
	@RequestMapping(value = "/echo", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	String echo(@RequestParam("message") String message) {
		message
	}

	@RequestMapping(value="/ping", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	String ping() {
		"pong"
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	DummyJsonWrapper json(@RequestBody DummyJsonWrapper json) {
		new DummyJsonWrapper(message: "received $json.message")
	}
	
	@RequestMapping(value = "/secured/user", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	String userSecured(String message) {
		"visited user secured page"
	}
	
	@RequestMapping(value = "/secured/admin", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	String adminSecured(String message) {
		"visited admin secured page"
	}
	
	static class DummyJsonWrapper {
		String message
	}
	
}
