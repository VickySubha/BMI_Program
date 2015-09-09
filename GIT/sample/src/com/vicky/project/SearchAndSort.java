package com.vicky.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.Customer;
import com.demo.QueryData;
@SuppressWarnings("serial")
public class SearchAndSort extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			PrintWriter out = resp.getWriter();
			String option = req.getParameter("option");

			PersistenceManager pm = PMF.get().getPersistenceManager();
			String queryString = "SELECT FROM " + Customer.class+"ORDER 'email'";

			Query a = pm.newQuery(queryString);
							@SuppressWarnings("unchecked")
							List<Customer> customers = (List<Customer>) a.execute();
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

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {

			String email = req.getParameter("Firstemail");
			System.out.println(email);
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
					while (user_email.equals(email)) {

						String emailParam = email;
						String queryStr = "SELECT FROM "
								+ Customer.class.getName()
								+ " WHERE email == '" + emailParam + "'";
						System.out.println("Query string " + queryStr);
						Query q = pm.newQuery(queryStr);
						String user_name = null;

						String user_dateofbirth = null;

						String user_gender = null;
						String user_mobileno = null;
						@SuppressWarnings("unchecked")
						List<Customer> results = (List<Customer>) q.execute();
						if (!results.isEmpty()) {
							for (Customer c : results) {
								user_email = c.getEmail();
								if (user_email.equalsIgnoreCase(email)) {

									user_name = c.getName();

									user_gender = c.getGender();

									user_dateofbirth = c.getdateofbirth();
									user_mobileno = c.getMobile();

									String Details = "User Name		:" + user_name
											+ "<br>Email address 	:"
											+ user_email
											+ "<br>Date of Birth	:"
											+ user_dateofbirth + "<br>Gender	:"
											+ user_gender
											+ "<br>Mobile number	:"
											+ user_mobileno + "<br><br>";

									System.out.println(Details);
									req.setAttribute("servlet_value", Details);
									RequestDispatcher rd = req
											.getRequestDispatcher("details.jsp");
									rd.forward(req, resp);

								}
							}
						}
					}
				}

			}
			String details = "Email ID does not exist";
			req.setAttribute("servlet_value", details);
			RequestDispatcher rd = req.getRequestDispatcher("details.jsp");

			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}