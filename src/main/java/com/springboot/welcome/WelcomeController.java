package com.springboot.welcome;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

//	//Auto wiring
//	@Autowired
//	private WelcomeService service;
//
//	@RequestMapping("/welcome")
//	public String welcome() {
//		return service.retrieveWelcomeMessage();
//	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "This is a welcome message";
	}
	
}