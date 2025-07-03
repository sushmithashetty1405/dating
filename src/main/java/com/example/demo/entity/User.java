package com.example.demo.entity;

import java.util.List;

import com.example.demo.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String email;
	private int age;
	private long phone;

	@Enumerated(EnumType.STRING)

	private UserGender gender;

	@ElementCollection
	private List<String> interest;// there are many interest for different ppl so it is list//one user many// interset
		
	private int  ageDiff;
	private int mic;

	
	

}
