package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//ponto no endereço do site
@RequestMapping("/hello")
@RestController
public class ServicoController {

	//MVC - Models(Tabelas) View (front-end) Controller (logica do programa, endereçamento)
	//JPA é interface
	
	@GetMapping("/get1")
	public String hello1() {
		return "Hey Get1 Hello World";
	} //seusite.com.br/hello/get1 vai aparecer "Hey get1 Hello World"
	
	@GetMapping("/get2")
	public String hello2() {
		return "Hey Get2 Hello World";
	}
	
	//injeção
		@Autowired
		private ServicoRepository repository;
		
		@GetMapping ("/servicos")
		public List<ServicoModel> pegarTodos() {		
			return repository.findAll();
		}

}
