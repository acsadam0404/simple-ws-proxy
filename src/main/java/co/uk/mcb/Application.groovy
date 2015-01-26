package co.uk.mcb;

import javax.servlet.FilterRegistration
import javax.servlet.ServletContext
import javax.servlet.ServletException

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.filter.CharacterEncodingFilter

@ComponentScan
@EnableAutoConfiguration
class Application   {

	static void main(String[] args) {
		SpringApplication.run(Application, args)
	}


	public void onStartup(ServletContext servletContext) throws ServletException {
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}
}
