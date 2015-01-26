package co.uk.mcb.rest


import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.FilterChainProxy
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

import co.uk.mcb.Application
import co.uk.mcb.security.SecurityConfiguration
import org.springframework.security.crypto.codec.Base64

import com.fasterxml.jackson.databind.ObjectMapper


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = [ Application.class, SecurityConfiguration.class ])
class EchoRestControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private FilterChainProxy filterChain;

	private MockMvc mvc;

	@Before
	public void setUp() {

		mvc = webAppContextSetup(context).addFilters(filterChain).build();

		SecurityContextHolder.clearContext();
	}

	@Test
	public void allowsAccessToJsonResource() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

		mvc.perform(post("/json").//
				headers(headers).content('{"message":"msg"}')).//
				andExpect(header().string("Content-Type", "application/json;charset=utf-8")).//
				andExpect(status().isOk()).//
				andDo(print());
	}

	@Test
	public void rejectsUserGetIfNoBasicAuth() throws Exception {

		mvc.perform(get("/secured/user").//
				accept("text/plain;charset=utf-8")).//
				andExpect(status().isUnauthorized()).//
				andDo(print());
	}
	
	
	@Test
	public void rejectsAdminGetIfNoBasicAuth() throws Exception {

		mvc.perform(get("/secured/admin").//
				accept("text/plain;charset=utf-8")).//
				andExpect(status().isUnauthorized()).//
				andDo(print());
	}


	@Test
	void allowUserGetWithUserBasicAuth() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, "text/plain;charset=utf-8");
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("user:user").getBytes())));

		mvc.perform(get("/secured/user").//
				headers(headers)).//
				andExpect(content().contentType("text/plain;charset=utf-8")).//
				andExpect(status().isOk()).//
				andDo(print());

	}
	
	@Test
	void recetAdminGetWithUserBasicAuth() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, "text/plain;charset=utf-8");
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("user:user").getBytes())));

		mvc.perform(get("/secured/admin").//
				headers(headers)).//
				andExpect(status().isForbidden()).//
				andDo(print());
	}
	
	
	@Test
	void allowAdminGetWithAdminBasicAuth() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, "text/plain".toString());
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("admin:admin").getBytes())));

		mvc.perform(get("/secured/user").//
				headers(headers)).//
				andExpect(content().contentType("text/plain;charset=utf-8")).//
				andExpect(status().isOk()).//
				andDo(print());

	}
}