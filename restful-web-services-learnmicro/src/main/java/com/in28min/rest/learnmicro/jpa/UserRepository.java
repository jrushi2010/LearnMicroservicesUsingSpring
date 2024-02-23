package com.in28min.rest.learnmicro.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28min.rest.learnmicro.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
