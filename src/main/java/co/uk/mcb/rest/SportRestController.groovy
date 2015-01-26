package co.uk.mcb.rest

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import co.uk.mcb.domain.Competition
import co.uk.mcb.domain.Sport
import co.uk.mcb.domain.Category

@RestController
@RequestMapping("/sports")
class SportRestController {
	private Sport[] sports
	
	SportRestController() {
		sports = [
			new Sport(id: "1", name: 'sportname1', categories: 
				[new Category(id: "11", name: "sport1cat1", competitions : [new Competition(id:"111", name: 'sport1cat1comp1'), new Competition(id:"112", name : 'sport1cat1comp2')]), 
				 new Category(id: "12", name: "sport1cat2")]), 
			new Sport(id: "2", name: 'sportname1')]
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	Sport[] list() {
		sports
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	Sport get(@PathVariable String id) {
		sports[Integer.parseInt(id)]
	}
	
	
	
}
