package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.entity.User;

@RestController
public class UserController {

	@Autowired
	
	UserService userservice;
	
	@PostMapping("/user")
	public ResponseEntity<?> saveuser( @RequestBody User u){
		return userservice.save(u);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<?>  findall(){
		
		return  userservice.findall();
	}

	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> findbyid(@PathVariable int id){
		return userservice.findbyid(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletebyid(@PathVariable int id) {
		
		return  userservice.deletebyid(id);
		
	}
	@GetMapping("/user/gender/male")   //endpoint to display the male
	
	public  ResponseEntity<?> findAllMaleUsers(){
		return userservice.findAllMaleUsers();
	}
	
	@GetMapping("/user/gender/female")
	
	public ResponseEntity<?>  findAllFemaleUser(){
		return userservice.findallfemale();
	}
	@GetMapping("/users/best-match/{id}/{top}")
	public ResponseEntity<?> findBestmatch(@PathVariable int id,@PathVariable int top){
		return userservice.findbestmatch(id,top);
	}
	
	
	@GetMapping("/user/search/name/{letters}")
	public ResponseEntity<?> searchbyname(@PathVariable String letters){
		return userservice.searchbyname(letters);
	}
	
	@GetMapping("user/search/email/{letters}")
	public ResponseEntity<?> searchbyemail(@PathVariable String letters){
		return userservice.searchbyemail(letters);
	}
	
}
