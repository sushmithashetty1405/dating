package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;
import com.example.demo.util.UserGender;

public interface UserRepository extends JpaRepository<User,Integer>{

	List<User> findByGender(UserGender male);

	
	@Query("select u from User u where u.name like ?1")
	List<User> searchByName(String letters);


	
	@Query("select u from User u where u.email like?1")
	List<User> searchByEmail(String letters);
	
}