package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.PostagemModel;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
		
		//injetando o repository
		@Autowired
		private PostagemRepository repository;
		
		//pegar todos os dados em lista
		@GetMapping
		public ResponseEntity<List<PostagemModel>> GetAll(){
			return ResponseEntity.ok(repository.findAll());
		}
		
		//pagina /postagens/1, por exemplo, retorna o dado de acordo com o ID colocado
		@GetMapping("/{id}")
		public ResponseEntity<PostagemModel> GetById(@PathVariable Long id){
			return repository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
		}
		
		//postagens/titulo/api, por exemplo, retorna o dado da tabela que o titulo inclui a palavra "api"
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity<List<PostagemModel>> GetByTitulo(@PathVariable String titulo){
			return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		}
}
