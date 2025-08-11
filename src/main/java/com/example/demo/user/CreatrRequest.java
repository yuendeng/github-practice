package com.example.demo.user;

import javax.persistence.Column;

import lombok.Data;

@Data
public class CreatrRequest {
	
	private int id;
	private String name;
	private int age;

}
