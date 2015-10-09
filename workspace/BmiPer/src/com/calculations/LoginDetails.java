package com.calculations;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.JDO.PMF;
import com.JDO.UserDetails;

public class LoginDetails {
	/*DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
	
	Entity e=new Entity("login time",name);	
	
	e.setProperty("name", name);
	e.setProperty("login time",login_time);
	
	ds.put(e);
	//request.setAttribute("name3", x);
	
	@SuppressWarnings("deprecation")
	Query Q=new Query("login time").addFilter("name",Query.FilterOperator.EQUAL,name);
	PreparedQuery preq=ds.prepare(Q);
	Entity currentUser=preq.asSingleEntity();

	long userLoginTime=(long)currentUser.getProperty("login time");
	System.out.println(userLoginTime);
	Calendar calendar = new GregorianCalendar();
	TimeZone timeZone = calendar.getTimeZone();
	String timeZoneId = timeZone.getID();
	 DateFormat df = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
	 df.setTimeZone(TimeZone.getTimeZone(timeZoneId));
	 //formatDateToString(date, "dd MMM yyyy hh:mm:ss a", "IST")
       //formatted value of current Date
       String currentTime= df.format(userLoginTime)+"  IST";*/
	void addUsersDetails(String userName,long loginTime, long logoutTime)
	{
		UserDetails userDetails = new UserDetails(userName.toLowerCase(),loginTime, logoutTime);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(userDetails);
		}
		finally{
			pm.close();
		}

	}
	public List<UserDetails> retrieving(String queryElement){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = "SELECT FROM " + UserDetails.class.getName()
				+ " WHERE userName == '" + queryElement + "'";
		Query queryString = pm.newQuery(queryStr);
		queryString.setOrdering("loginTime desc");
		@SuppressWarnings("unchecked")
		List<UserDetails> data = (List<UserDetails>) queryString.execute();
		return(data);
		}
	
}
