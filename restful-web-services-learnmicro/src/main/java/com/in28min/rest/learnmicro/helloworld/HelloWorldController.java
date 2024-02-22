package com.in28min.rest.learnmicro.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloWorldController {

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "hello world";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello-world-bean");
	}
	
}
