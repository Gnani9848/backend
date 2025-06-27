package com.secur.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
public class UserCredential {

	
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Id
    @Column(nullable = false)
	@NotBlank(message = "username cannot be blank")
	private String username;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Role cannot be blank")
    private String role;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserCredential(@NotBlank(message = "User ID cannot be blank") String userId,
			@NotBlank(message = "username cannot be blank") String username,
			@NotBlank(message = "Name cannot be blank") String name,
			@NotBlank(message = "Password cannot be blank") String password,
			@Email(message = "Invalid email format") String email,
			@NotBlank(message = "Role cannot be blank") String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public UserCredential() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
