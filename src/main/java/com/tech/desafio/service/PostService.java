package com.tech.desafio.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.desafio.model.Post;
import com.tech.desafio.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Object criarPost(Post post){
		return repository.save(post);
	}
	
	public Optional<Post> atualizarPost(Post post){
		return repository.findById(post.getId()).map(resp ->{
			resp.setTitle(post.getTitle());
			resp.setDescription(post.getDescription());
			resp.setBody(post.getBody());
			resp.setUpdated_at(new Date(System.currentTimeMillis()));
			return Optional.ofNullable(repository.save(resp));
		}).orElseGet(() ->{
			return Optional.empty();
		});
	}
	
	
	
}
