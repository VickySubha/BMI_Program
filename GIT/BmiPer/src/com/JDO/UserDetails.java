package com.JDO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable
public class UserDetails {
	 @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Key key;
	

	@Persistent
	String userName;
	@Persistent     
	long loginTime;
	@Persistent     
	long logoutTime;
	
	public String getUserName() { 
		return userName;
	}
	public long getLoginTime() { 
		return loginTime;
	}
	public long getLogoutTime() { 
		return logoutTime;
	}
	public void setuserName(String userName) {
		this.userName= userName;
	}
	public void setloginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public void setlogoutTime(long logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	
	public UserDetails(String userName, long loginTime, long logoutTime)
	{
		this.userName=userName;
		this.loginTime=loginTime;
		this.logoutTime=logoutTime;
	}
}
