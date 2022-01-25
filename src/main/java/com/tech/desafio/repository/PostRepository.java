package com.tech.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.desafio.model.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long>{	
	
}
