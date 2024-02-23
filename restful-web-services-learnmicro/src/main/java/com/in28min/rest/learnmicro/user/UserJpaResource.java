package com.in28min.rest.learnmicro.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28min.rest.learnmicro.jpa.PostRepository;
import com.in28min.rest.learnmicro.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserDaoService service;
	
	private UserRepository repository;
	
	private PostRepository postRepository;
	
	public UserJpaResource(UserDaoService service,UserRepository repository,PostRepository postRepository) {
		this.service = service;
		this.repository = repository;
		this.postRepository = postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id){		
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUsed = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUsed.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){		
		repository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id){		
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post){		
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
