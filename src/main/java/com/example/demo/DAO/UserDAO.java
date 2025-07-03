package com.example.demo.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.UserGender;
@Repository
public class UserDAO {
	@Autowired
UserRepository userrepo;

	public User save(User u) {
		// TODO Auto-generated method stub
		return userrepo.save(u);
	}

	public List<User> findall() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
		
	}

	public Optional<User> findbyid(int id) {
		// TODO Auto-generated method stub
		return  userrepo.findById(id);
	}

	public void deletebyid(int id) {
		// TODO Auto-generated method stub
		 userrepo.deleteById(id);
		 
	}

	public List<User> findAllMaleUsers() {
		// TODO Auto-generated method stub
		return  userrepo.findByGender(UserGender.MALE);//enum name
	
	}
	public List<User> findAllfemaleUsers() {
		// TODO Auto-generated method stub
		return userrepo.findByGender(UserGender.FEMALE);
	}




	}