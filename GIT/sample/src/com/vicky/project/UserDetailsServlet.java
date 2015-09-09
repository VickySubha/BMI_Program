package com.vicky.project;

import java.io.IOException;
import com.demo.QueryData;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import com.vicky.project.PMF;
import com.demo.Customer;

import javax.jdo.Query;

@SuppressWarnings("serial")
public class UserDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			PrintWriter out = resp.getWriter();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String queryString = "SELECT FROM " + Customer.class.getName();
			Query a = pm.newQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Customer> customers = (List<Customer>) a.execute();
			out.println("<html><body><h4>");
			if (!customers.isEmpty()) {
				out.println("<table cellspacing=3 cellpadding=3 border=2>");
				out.println("<tr><td>User Name</td><td>Email Address</td><td>Date of Birth</td><td>Gender</td><td>Mobile Number</td></tr>");
				for (Customer e : customers) {
					String user_email = e.getEmail();
					String user_name = e.getName();
					String user_gender = e.getGender();
					String user_dateofbirth = e.getdateofbirth();
					String user_mobileno = e.getMobile();

					out.println("<tr><td>" + user_name + "</td><td>"
							+ user_email + "</td><td>" + user_dateofbirth
							+ "</td><td>" + user_gender + "</td><td>"
							+ user_mobileno + "</td></tr>");

				}
				out.println("<a href='details.jsp'>back</a>");
				out.println("<div align='right'><form action='/searchandsort' method='post'> ");
				out.println("Sort<select name='option'><option value='Ascending'>Ascending</option><option value='Descending'>Descending</option></select>");
			}
			out.println("<input type='submit'><form></div></table></h4></body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String password = req.getParameter("p1");
			String email = req.getParameter("Email");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String emailParam = email;
			String queryStr = "SELECT FROM " + Customer.class.getName()
					+ " WHERE email == '" + emailParam + "'";
			System.out.println("Query string " + queryStr);
			Query q = pm.newQuery(queryStr);
			String user_password = null;
			@SuppressWarnings("unchecked")
			List<Customer> results = (List<Customer>) q.execute();
			if (!results.isEmpty()) {
				for (Customer c : results) {

					String epassword = c.getPassword();
					user_password=cipher(epassword, -5);
					if (password.equals(user_password)) {

						try {
							pm.deletePersistent(c);
						} finally {
							pm.close();
						}
					}else{
						resp.sendRedirect("logout.jsp");
					}
					resp.sendRedirect("details.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String cipher(String sentence, int offset) {
		  String s = "";
		  for(int i = 0; i < sentence.length(); i++) {
		    char c = (char)(sentence.charAt(i));
		    if (c >= 'A' && c <= 'Z') {     
		      s += (char)((c - 'A' + offset) % 26 + 'A');
		    } else if (c >= 'a' && c <= 'z') {
		      s += (char)((c - 'a' + offset) % 26 + 'a');
		    } else {
		      s += c;
		    }
		  }
		  return s;
		}
		
}