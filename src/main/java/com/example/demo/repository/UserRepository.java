package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;
import com.example.demo.util.UserGender;

public interface UserRepository extends JpaRepository<User,Integer>{

	List<User> findByGender(UserGender male);
}