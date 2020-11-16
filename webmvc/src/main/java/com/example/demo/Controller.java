package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class Controller implements WebMvcConfigurer {
	
	//acessar o site pelo localhost 8080
	public void addViewControllers(ViewControllerRegistry index) {
		index.addViewController("/").setViewName("forward:/index.html");
		
	}
	
	//injetar o repository
	@Autowired
	private ManutencaoRepository repository;
	
	//4 metodos, get, post, put, delete
	//get by id, nome, titulo etc
	//localhost:8080/manutencoes
	@GetMapping("/manutencoes")
	public List<ManutencaoTable> buscarTodos(){
		return repository.findAll();
	}
	
//	//buscar um dado de acordo com o parametro q eu colocar (ID)
//	@GetMapping("/manutencoes/{id}")
//	public Optional<ManutencaoTable> buscarUm(@PathVariable Long id){
//		return repository.findById(id);
//	}
	
	//post é inserir, neste caso na mesma URL (localhost:8080/manutencoes).
	//o que estiver no corpo sera meu objeto, salve e retorne ele;
	@PostMapping ("/manutencoes")
	public ManutencaoTable criar(@RequestBody ManutencaoTable objetinho) {
		repository.save(objetinho);
		return objetinho;
	}
	
	//atualizar
	@PutMapping("/manutencoes/{id}")
	public ManutencaoTable atualizar(@PathVariable Long id, @RequestBody ManutencaoTable objetinho) {
		objetinho.setId(id);
		repository.save(objetinho);
		return objetinho;
	}
	
	@GetMapping("/manutencoes/teste/{nome}")
	public List<ManutencaoTable> buscarPorNome (@PathVariable String nome) {
		return repository.findByNome(nome);
	}
	
	@GetMapping("/manutencoes/{nome}/{categoria}")
	public Optional<ManutencaoTable> findByNomeAndCategoria (@PathVariable String nome, @PathVariable String categoria){
		return repository.findByNomeAndCategoria(nome,categoria);
	}
	
	@DeleteMapping("/manutencoes/{id}")
	public void deletarUm(@PathVariable Long id){
		repository.deleteById(id);
	}
	
//	//deste jeito nao retornar erro
//	@GetMapping("/manutencoes/id/{id}")
//	public Optional<ManutencaoTable> buscarUm(@PathVariable Long id) {
//		return repository.findById(id);
//	}
	
	@GetMapping("/manutencoes/id/{id}")
	public ResponseEntity<ManutencaoTable> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@Autowired 
	private Services service;
	
	@GetMapping("/teste")
	    public ResponseEntity<List<ManutencaoTable>> listAllItens() {
	        List<ManutencaoTable> itens= service.findAllItens();
	        if(itens.isEmpty()){
	            return new ResponseEntity<List<ManutencaoTable>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<ManutencaoTable>>(itens, HttpStatus.ACCEPTED);
	    }



}
