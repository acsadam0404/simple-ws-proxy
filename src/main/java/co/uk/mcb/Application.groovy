package co.uk.mcb;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan

import co.uk.mcb.math.BigDecimalRounding

@ComponentScan
@EnableAutoConfiguration
class Application {

	static void main(String[] args) {
		SpringApplication.run(Application, args)
	}
}
