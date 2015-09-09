package com.demo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
 
@PersistenceCapable
public class Customer {
 
	 @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Key key;
	 
    @Persistent
    private String name;
    @Persistent
    private String password;
    @Persistent
    private String dateofbirth;
 
    @Persistent
    private String email;
    @Persistent
    private String gender;
    @Persistent
    private String mobile;
    
    public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
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
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getdateofbirth() {
		return dateofbirth;
	}

	public void setdateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	
	
    //getter and setter methods
}


