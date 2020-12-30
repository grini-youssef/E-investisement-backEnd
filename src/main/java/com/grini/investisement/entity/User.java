package com.grini.investisement.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long userId;
	 
	 private String username;
	 
	 private String password;
	 
	 private String email;
	 
	 private String contact;
	 
	 private Instant created;
	 
	 private boolean enabled;


}
