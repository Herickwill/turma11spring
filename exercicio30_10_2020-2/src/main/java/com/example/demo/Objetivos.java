package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class Objetivos {

	@GetMapping("/app2")
	public String hello1() {
		return "Os objetivos dessa semana foram aprender o basico de Spring! ";
	} 
	
}