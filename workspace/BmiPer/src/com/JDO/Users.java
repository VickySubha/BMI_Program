package com.JDO;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Users 
{                                                                      
	@PrimaryKey
	String name;

	@Persistent     
	String password;
	
	public String getPassword() { 
		return password;
	}
	
	public Users(String name, String password)
	{
		this.name=name;
		this.password=password;
	}
}
