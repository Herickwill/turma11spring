package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class Mentalidades {

	@GetMapping("/app1")
	public String hello1() {
		return "Olá! As mentalidades usadas foram persistência e atenção ao detalhe.";
	} 
	
}
