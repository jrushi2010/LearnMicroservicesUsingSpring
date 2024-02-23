package com.in28min.rest.learnmicro.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28min.rest.learnmicro.user.Post;
import com.in28min.rest.learnmicro.user.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
