package com.vicky.project;
import com.demo.QueryData;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.Customer;

@SuppressWarnings("serial")
public class UpdateServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			PrintWriter out = resp.getWriter();
			String old_password = req.getParameter("p1");
			String new_password = req.getParameter("p2");
			String email = req.getParameter("Email");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String emailParam = email;
			String queryStr = "SELECT FROM " + Customer.class.getName()
					+ " WHERE email == '" + emailParam + "'";
			System.out.println("Query string " + queryStr);
			Query q = pm.newQuery(queryStr);
			String user_password = null;
			List<Customer> results = (List<Customer>) q.execute();
			if (!results.isEmpty()) {
				for (Customer c : results) {

					user_password = c.getPassword();
					if (old_password.equals(user_password)) {

						try {

							user_password = new_password;
							c.setPassword(user_password);
							pm.makePersistent(c);
						} finally {
							pm.close();
						}
						String details = "Password Changed Successfully..!";
						req.setAttribute("servlet_value", details);
						RequestDispatcher rd = req
								.getRequestDispatcher("details.jsp");

						rd.forward(req, resp);
						resp.sendRedirect("details.jsp");
					} else {

						out.println("alert('sorry!, Invalid Attempt');");
						resp.sendRedirect("signin.jsp");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String name = req.getParameter("FirstName");

			String gender = req.getParameter("Gender");
			String mobile = req.getParameter("MobileNumber");

			String dateofbirth = req.getParameter("date");
			String email = req.getParameter("Email");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String emailParam = email;
			String queryStr = "SELECT FROM " + Customer.class.getName()
					+ " WHERE email == '" + emailParam + "'";
			System.out.println("Query string " + queryStr);
			Query q = pm.newQuery(queryStr);

			List<Customer> results = (List<Customer>) q.execute();
			if (!results.isEmpty()) {
				for (Customer c : results) {

					if (name != null) {
						c.setName(name);
					}
					if (gender != null) {
						c.setGender(gender);

					}
					if (mobile != null) {
						c.setMobile(mobile);
					}
					if (dateofbirth != null) {
						c.setdateofbirth(dateofbirth);
					}

					try {
						pm.makePersistent(c);

						// Object a=pm.getObjectById(Customer.class,
						// c.getEmail());
						System.out.println(pm.getObjectId(c));
						// System.out.println(a);
						resp.sendRedirect("details.jsp");
					} finally {
						pm.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}