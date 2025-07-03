package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data

public class MatchingUser 
{
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	private com.example.demo.util.UserGender gender;
	private List<String> interests;
	private int ageDiff;
	private int mic;
}