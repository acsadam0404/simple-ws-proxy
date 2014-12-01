package co.uk.mcb

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EchoRestController {
	@RequestMapping(value = "/echo")
	@ResponseBody
	String echo(@RequestParam("message") String message) {
		return message
	}
}
