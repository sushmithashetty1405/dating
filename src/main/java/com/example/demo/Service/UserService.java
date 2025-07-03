package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.UserDAO;
import com.example.demo.dto.MatchingUser;
import com.example.demo.entity.User;
import com.example.demo.util.UserGender;

@Service
public class UserService<MatchingUser> {
	@Autowired
UserDAO userdao;

	public ResponseEntity<?> save(User u) {
		
 User saveduser=userdao.save(u);
 return ResponseEntity.status(HttpStatus.CREATED).body(saveduser);
	}

	public ResponseEntity<?> findall() {
List<User>  list=userdao.findall();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	public ResponseEntity<?> findbyid( int id) {
		// TODO Auto-generated method stub
		Optional<User> optional=userdao.findbyid(id);
		if(optional.isPresent()) {
			User user = optional.get();
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		}
		else {
			return ResponseEntity.status(HttpStatus.CREATED).body("no data found");

		}

	}

	public ResponseEntity<?> deletebyid(int id) {
		// TODO Auto-generated method stub;
		Optional<User> optional=userdao.findbyid(id);
		if(optional.isPresent()) {
			
			userdao.deletebyid(id);
			return ResponseEntity.status(201).body("id deleted");
			
		}
		return ResponseEntity.status(201).body("id not found");
	}

	public ResponseEntity<?> findAllMaleUsers() {
		List<User> maleUsers=userdao.findAllMaleUsers();
		
		if(maleUsers.isEmpty()) {
 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no male user present in the base table");
			
		
		}
		else {
			
			return ResponseEntity.status(HttpStatus.OK).body(maleUsers);
			
		}
		}

	public ResponseEntity<?> findallfemale() {
		// TODO Auto-generated method stub
		List<User> list=userdao.findAllfemaleUsers();
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
		
	}
	
	
	w

	public ResponseEntity<?> findbestmatch(int id, int top) {
		// TODO Auto-generated method stub
		return null;
		
		Optional<User> optional=userdao.findbyid(id);
		if(optional.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("inavalid user id");
		}
		User user=optional.get();
		
		List<User> users=null;
		
		if(user.getGender().equals(UserGender.MALE)) {
			users=userdao.findAllfemaleUsers();
		}
		else {
			users=userdao.findall();
		}
		
		List<MatchingUser> matchingUsers=new ArrayList<>();
		for(User u:users) {
			MatchingUser mu=new MatchingUser();
			mu.setId(u.getId());
			mu.setName(u.getEmail());
			mu.setPhone(u.getPhone());
			mu.setAge(u.getAge());
			mu.setInterests(u.getInterest());
			
			mu.setGender(u.getGender());
			mu.setAgeDiff(Maths.abs(user.getAge()-u.getAge()));
			
			
			List<String> interest1=user.getInterest();
			List<String> interest2=u.getInterest();
			
			int mic=0;
			
			for(String s:interests1) {
				if(interests2.contains(s)) {
					mic++;
				}
			}
			mu.setMic(mic);
			
			matchingUsers.add(mu);
			
		}
		Comparator<MatchingUser> c=new UserSorting();
		
		Collections.sort(matchingUsers,c);
		List<MatchingUser> result=new ArrayList<>();
		for(MatchingUser mu:matchingUsers) {
			if(top==0) {
				break;
			}
			else {
				result.add(mu);
				top--;
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
		
	
	
	
}
