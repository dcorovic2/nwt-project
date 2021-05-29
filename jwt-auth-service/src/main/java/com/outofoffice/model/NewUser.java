package com.outofoffice.model;

import java.util.List;


import io.swagger.annotations.ApiModelProperty;

public class NewUser {
  
	  private String username;
	  private String email;
	  private String password;  
	  
	  public NewUser() {}
	  
	  public NewUser(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	}
