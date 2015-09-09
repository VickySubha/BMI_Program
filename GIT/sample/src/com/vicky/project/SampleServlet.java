package com.vicky.project;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import com.vicky.project.PMF;
import com.demo.Customer;
import com.demo.QueryData;
import com.google.appengine.repackaged.com.google.gson.JsonObject;

import javax.jdo.Query;

@SuppressWarnings("serial")
public class SampleServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String name = req.getParameter("FirstName")+" " +req.getParameter("LastName");
			String password = req.getParameter("password");
			String epassword = cipher(password, 5);
			
			String email = req.getParameter("Email");
			String gender = req.getParameter("Gender");
			String mobile = req.getParameter("MobileNumber");
			String dateofbirth = req.getParameter("date");
	
			Customer c = new Customer();
			c.setName(name);
			c.setPassword(epassword);

			c.setGender(gender);
			c.setMobile(mobile);
			c.setdateofbirth(dateofbirth);

			PersistenceManager pm = PMF.get().getPersistenceManager();
			/*String queryStr = "SELECT FROM " + Customer.class.getName();
			Query q = pm.newQuery(queryStr);
			@SuppressWarnings("unchecked")
			List<Customer> customers = (List<Customer>) q.execute();*/
JsonObject customers=new QueryData();
			if (!customers.isEmpty()) {
				for (Customer e : customers) {
					String user_email = e.getEmail();
					System.out.println(user_email);
					while (user_email.equalsIgnoreCase(email)) {
						String Details = "The email address already present";
						req.setAttribute("servlet_value", Details);
						RequestDispatcher rd = req
								.getRequestDispatcher("signup.jsp");

						rd.forward(req, resp);
						resp.sendRedirect("signup.jsp");
					}
				}
			}
			c.setEmail(email);
			try {
				pm.makePersistent(c);

				// Object a=pm.getObjectById(Customer.class, c.getEmail());
				System.out.println(pm.getObjectId(c));
				// System.out.println(a);
				resp.sendRedirect("signin.jsp");
			} finally {
				pm.close();
			}
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
			String queryString = "SELECT FROM " + Customer.class.getName();
			ServletContext context = getServletContext();
			context.setAttribute("email", email);
			Query a = pm.newQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Customer> customers = (List<Customer>) a.execute();

			if (!customers.isEmpty()) {
				for (Customer e : customers) {
					String user_email = e.getEmail();

					System.out.println(user_email);
					while (user_email.equalsIgnoreCase(email)) {

						String emailParam = email;
						String queryStr = "SELECT FROM "
								+ Customer.class.getName()
								+ " WHERE email == '" + emailParam + "'";
						System.out.println("Query string " + queryStr);
						Query q = pm.newQuery(queryStr);
						String user_name = null;
						String user_password = null;
						String user_dateofbirth = null;

						String user_gender = null;
						String user_mobileno = null;
						@SuppressWarnings("unchecked")
						List<Customer> results = (List<Customer>) q.execute();
						if (!results.isEmpty()) {
							for (Customer c : results) {
								user_email = c.getEmail();
								if (user_email.equals(email)) {

									user_name = c.getName();
									String epassword = c.getPassword();
									user_password = cipher(epassword, -5);
									System.out.println(user_password);
									user_gender = c.getGender();
									
									user_dateofbirth = c.getdateofbirth();
									user_mobileno = c.getMobile();
									context.setAttribute("name", user_name);
									context.setAttribute("date", user_dateofbirth);
									context.setAttribute("gender", user_gender);
									context.setAttribute("mobile", user_mobileno);
									// String k=e.getKey("Customer",
									// "Vignesh").toString();
									String Details = "User Name		:" + user_name
											+ "<br>Email address 	:"
											+ user_email
											+ "<br>Date of Birth	:"
											+ user_dateofbirth + "<br>Gender	:"
											+ user_gender
											+ "<br>Mobile number	:"
											+ user_mobileno + "<br><br>";

									if (password.equals(user_password)) {
										// out.println("login succesfull");
										// out.println("alert('sucesss');");
										System.out.println(Details);
										req.setAttribute("servlet_value",
												Details);
										RequestDispatcher rd = req
												.getRequestDispatcher("details.jsp");
										rd.forward(req, resp);

									} else {
										String details = "Sorry!, Invalid Attempt";
										req.setAttribute("servlet_value",
												details);
										RequestDispatcher rd = req
												.getRequestDispatcher("signin.jsp");

										rd.forward(req, resp);

										resp.sendRedirect("signin.jsp");
									}
								}
							}
						}
					}
				}
				String details = "Email ID does not exist";
				req.setAttribute("servlet_value", details);
				RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");

				rd.forward(req, resp);
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
