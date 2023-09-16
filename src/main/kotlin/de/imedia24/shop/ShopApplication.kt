package de.imedia24.shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

fun main(args: Array<String>) {
	runApplication<ShopApplication>(*args)
}

@Bean
fun productApi(): Docket? {
	return Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors.basePackage("de.imedia24.shop")).build()
}
@SpringBootApplication
@EnableSwagger2
class ShopApplication