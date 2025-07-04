package com.example.demo.dto;

import java.util.List;

import com.example.demo.util.UserGender;

import lombok.Data;

@Data
public class MatchingUser {
    private int id;
    private String name;
    private String email;
    private int age;
    private long phone;
    private UserGender gender;
    private List<String> interests;
    private int mic;       // Number of matching interests
    private int ageDiff;   // Age difference
}
