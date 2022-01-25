package com.tech.desafio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tech.desafio.model.Post;
import com.tech.desafio.repository.PostRepository;
import com.tech.desafio.service.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {
	
	@Autowired
	private PostService service;
	
	@Autowired
	private PostRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Post>> buscarTodos(){
		List<Post> objetoLista = repository.findAll();
		
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Post> buscarPorId(@PathVariable(value = "id") Long id){
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElseThrow(() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Id inexistente, passe um Id válido para pesquisa");
				});
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Post> cadastrar(@RequestBody Post post){
		return ResponseEntity.status(201).body(repository.save(post));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Post> atualizar(@RequestBody Post post){
		return service.atualizarPost(post).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Id inexistente, passe um Id válido para pesquisa");
				});
	}
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletar (@PathVariable(value = "id") Long id){
		return repository.findById(id).map(resp ->{
			repository.deleteById(id);
			return ResponseEntity.status(204).build();
		}).orElseThrow(() ->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"ID inexistente, passe um ID valido para deletar!.");
		});
	}
	

}
