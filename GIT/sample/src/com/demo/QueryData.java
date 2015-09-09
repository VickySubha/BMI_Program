package com.demo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.vicky.project.PMF;

public class QueryData {
	public Object retrieving(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = "SELECT FROM " + Customer.class.getName();
		Query q = pm.newQuery(queryStr);
		@SuppressWarnings("unchecked")
		Object customers = (List<Customer>) q.execute();
		return(customers);
		}
	public List<Customer> retrieving(String queryElement){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = "SELECT FROM " + Customer.class.getName();
		Query q = pm.newQuery(queryStr);
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>) q.execute();
		return(customers);
		}
	
}
