package com.dtag.bm.process.service_order_management_process;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import com.dtag.bm.process.service_order_management_process.service.ServiceOrderingSrvImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan("com.*")
@EnableAutoConfiguration(exclude = { HypermediaAutoConfiguration.class })
@EnableMongoRepositories(value = { "com.*" })
public class ServiceOrderingApplication {
	
	@Value("${apiVersion}")
	private String apiVersion;

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderingApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public ServiceOrderingSrvImpl ServiceInventoryService() {
	    return new ServiceOrderingSrvImpl();
	}
	
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("serviceOrderManagement").apiInfo(apiInfo()).select()
				.paths(regex("/serviceOrderManagement/.*")).build().directModelSubstitute(XMLGregorianCalendar.class, MixIn.class);
		///ServiceInventoring.
	}
	
	public static interface MixIn {
		@JsonIgnore
		public void setYear(int year);
	}

	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("ServiceOrdering Service APIs").description("ServiceOrdering Service APIs").
				termsOfServiceUrl("http://techmahindra.com").contact("TechMahindra").license("Techamhindra Licensed").licenseUrl("http://techmahindra.com").
				version(apiVersion).build();
	}
	

}
