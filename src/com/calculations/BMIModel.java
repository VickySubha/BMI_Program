package com.calculations;


import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import com.JDO.*;

public class BMIModel 
{	
	void addUsers(String name,String password)
	{
		Users users = new Users(name.toLowerCase(),password);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(users);
		}
		finally{
			pm.close();
		}

	}
	
	boolean checkUser(String name, String password)
	{
		try
		{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Users.class.getSimpleName(), name.toLowerCase());
        Users users = pm.getObjectById(Users.class, k);
		return users.getPassword().equals(password);
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
}
