package com.calculations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import com.JDO.PMF;
import com.JDO.UserDetails;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.valuepack.ValueProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class BMIController {
	long login_time, logout_time;

	@RequestMapping("/")
	public ModelAndView welcomeLocator() {
		ModelAndView model = new ModelAndView("Welcome");
		return model;
	}

	@RequestMapping("/Index")
	public ModelAndView enterIndex() {
		ModelAndView model = new ModelAndView("Index");
		return model;
	}

	@RequestMapping("/StandardBMICalculator")
	public ModelAndView standardBmiCalculator(@RequestParam("feet") int feet,
			@RequestParam("inch") int inch, @RequestParam("weight") float weight) {
		ModelAndView model = new ModelAndView("BMIResult");
		ValueProvider provider = new ValueProvider();
		Float bmi;
		int height = ((feet * 12) + inch);
		weight = weight * 2.2f;
		bmi = ((float) weight / (height * height)) * provider.getValue();
		model.addObject("bmi", bmi);
		return model;
	}

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ModelAndView oauthsuccess(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {
		try{
		response.setContentType("text/html");

		String authorised_code = request.getParameter("code");
		// response.getWriter().println("Authorization code is" +
		// Authorised_code);
		String client_id = "659856324305-8hi9lh0qk0en804b1odfcao3ii9fgn42.apps.googleusercontent.com";
		String client_secret = "GJscoH0vjMUrcGZj0TjhOwNC";
		String redirect_uri = "http://1-dot-oauthmvcc-1045.appspot.com/log";

		String grant_type = "authorization_code";

		if (authorised_code != null) {
			ModelAndView model = new ModelAndView("Index");
			request.getSession().setAttribute("isLoggedIn", true);
			URL u = new URL(
					"https://www.googleapis.com/oauth2/v3/token?client_id="
							+ client_id + "&client_secret=" + client_secret
							+ "&redirect_uri=" + redirect_uri + "&grant_type="
							+ grant_type + "&code=" + authorised_code);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content_Type",
					"application/x-www-form-urlencoded");
			con.setDoOutput(true);
			// response.getWriter().println(con.getResponseCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line;
			String content = "";
			while ((line = rd.readLine()) != null) {
				content += line;
			}
			// response.getWriter().println(content);
			rd.close();

			JsonObject obj = (JsonObject) new JsonParser().parse(content);
			String access_token = obj.get("access_token").getAsString();
			if (access_token != null) {
				URL url = new URL(
						"https://www.googleapis.com/oauth2/v1/userinfo?access_token="
								+ access_token);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				BufferedReader r = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String l;
				String content1 = "";
				while ((l = r.readLine()) != null) {
					content1 += l;
				}
				r.close();
				JsonObject obj1 = (JsonObject) new JsonParser().parse(content1);
				String name = obj1.get("name").getAsString();
				String nam = obj1.get("picture").getAsString();
				Date date = new Date();
				long loginTime = date.getTime();

				String imageurl = "";
				if (nam.equals("https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg")) {
					imageurl = "http://images.fineartamerica.com/images/artworkimages/mediumlarge/1/single-yellow-flower-nell-werner.jpg";

				} else {
					imageurl = nam;
				}
				request.setAttribute("name", name);
				request.setAttribute("name2", imageurl);
				long logoutTime = 0;

				LoginDetails userDetails = new LoginDetails();
				userDetails.addUsersDetails(name, loginTime, logoutTime);
				List<UserDetails> userDetail = userDetails.retrieving(name);
				if (!userDetail.isEmpty()) {
					for (UserDetails user : userDetail) {
						loginTime = user.getLoginTime();
						break;
					}
				}
				Calendar calendar = new GregorianCalendar();
				TimeZone timeZone = calendar.getTimeZone();
				String timeZoneId = timeZone.getID();
				DateFormat dateformat = new SimpleDateFormat(
						"dd/MM/yyyy  HH:mm:ss");
				dateformat.setTimeZone(TimeZone.getTimeZone("IST"));
				// formatDateToString(date, "dd MMM yyyy hh:mm:ss a", "IST")
				// formatted value of current Date
				String currentTime = dateformat.format(loginTime) +   timeZoneId;
				request.setAttribute("name3", currentTime);
				request.getRequestDispatcher("Index.jsp").forward(request,
						response);

				return model;

				/*
				 * response.getWriter().println("<h2>Hi!!</h2>" + name);
				 * response.getWriter().println(
				 * "<h1>You are Logged in Successfully!!!</h1>");
				 * response.getWriter().print("<h3>Your mail id is </h3>" +
				 * email);
				 */
			} else {
				response.getWriter().println("no access token");
			}
		} else {
			response.getWriter().println("Access Denied");
		}
		
		}catch (Exception e){
			e.printStackTrace();
		}return null;
	}

	@RequestMapping("/MetricBMICalculator")
	public ModelAndView metricBmiCalculator(
			@RequestParam("height") float height,
			@RequestParam("weight") int weight) {
		ModelAndView model = new ModelAndView("BMIResult");
		Float bmi;
		bmi = ((float) weight / (height * height));
		model.addObject("bmi", bmi);
		return model;
	}

	@RequestMapping("/HeightCalculator")
	public ModelAndView heightCalculator(@RequestParam("weight") int weight,
			@RequestParam("bmi") float bmi) {
		ModelAndView model = new ModelAndView("HeightResult");
		Float height;
		height = (float) Math.sqrt((weight / bmi));
		model.addObject("height", height);
		return model;
	}

	@RequestMapping("/WeightCalculator")
	public ModelAndView weightCalculator(@RequestParam("height") float height,
			@RequestParam("bmi") float bmi) {
		ModelAndView model = new ModelAndView("WeightResult");
		Float weight;
		weight = (float) (bmi * (height * height));
		model.addObject("weight", weight);
		return model;
	}
	@RequestMapping(value="/GetuserData", method = RequestMethod.POST)
	
	public ModelAndView getUserData(@RequestParam("name1") String name,HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException { 
		ModelAndView model = new ModelAndView("Display");
		try{
		PrintWriter out = response.getWriter();
		System.out.println(name);
		LoginDetails loginDetail=new LoginDetails();
		List<UserDetails> data=loginDetail.retrieving(name);
		System.out.println(data);
		
		// Collections.sort(data);
		out.println("<html><body><h4>");
		if (!data.isEmpty()) {
			out.println("<table cellspacing=3 cellpadding=3 border=4>");
			out.println("<tr><td>User Name</td><td>Login Time</td><td>Logout Time</td></tr>");
			for (UserDetails user : data) {
				long loginTime = user.getLoginTime();
				long logoutTime=user.getLogoutTime();
				String userName=user.getUserName();
				Calendar calendar = new GregorianCalendar();
				TimeZone timeZone = calendar.getTimeZone();
				String timeZoneId = timeZone.getID();
				DateFormat dateformat = new SimpleDateFormat(
						"dd/MM/yyyy  HH:mm:ss");
				dateformat.setTimeZone(TimeZone.getTimeZone("IST"));
				String loggedInTime = dateformat.format(loginTime) +   timeZoneId;
				String loggedOutTime=null;
				if(logoutTime!=0){
				loggedOutTime = dateformat.format(logoutTime) +   timeZoneId;
				}else{
					loggedOutTime="-";
				}
				out.println("<tr><td>" + userName + "</td><td>"
						+ loggedInTime + "</td><td>"
						+ loggedOutTime + "</td></tr>");

			}
			out.println("<a href='Index.jsp'>back</a>");
			}
		out.println("</div></table></h4></body></html>");
		
				
		
	}
		catch(Exception e){
		e.printStackTrace();	
		}
		return model;

		}

	@RequestMapping(value = "/ValidateLogin", method = RequestMethod.POST)
	public ModelAndView validateLogin(@RequestParam("uName") String name,
			@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model;
		BMIModel bmiModel = new BMIModel();
		try{
		if (bmiModel.checkUser(name, password)) {
			model = new ModelAndView("Index");
			request.getSession().setAttribute("isLoggedIn", true);
			String imageurl = "http://images.fineartamerica.com/images/artworkimages/mediumlarge/1/single-yellow-flower-nell-werner.jpg";

			request.setAttribute("name", name);
			request.setAttribute("name2", imageurl);

			Date date = new Date();
			long loginTime = date.getTime();
			long logoutTime = 0;

			LoginDetails userDetailed = new LoginDetails();
			userDetailed.addUsersDetails(name, loginTime, logoutTime);
			LoginDetails userDetails = new LoginDetails();
			List<UserDetails> userDetail = userDetails.retrieving(name);
			if (!userDetail.isEmpty()) {
				for (UserDetails user : userDetail) {
					loginTime = user.getLoginTime();
					break;
				}
			}
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");

			// formatted value of current Date
			String currentTime = df.format(loginTime) + "  IST";
			request.setAttribute("name3", currentTime);
			request.getRequestDispatcher("Index.jsp")
					.forward(request, response);
		} else {
			model = new ModelAndView("WrongEntry");
		}return model;
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/AddUsers", method = RequestMethod.POST)
	public ModelAndView addUsers(@RequestParam("uName") String name,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		ModelAndView model = null;
		try{
		if ((name != null) && (name != "")) {
			model = new ModelAndView("Success");
			request.getSession().setAttribute("isLoggedIn", true);
			BMIModel bmiModel = new BMIModel();
			bmiModel.addUsers(name, password);
		} else
			model = new ModelAndView("BlankEntries");
		}catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("/ajax")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("GetUserData", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public ModelAndView logout(@RequestParam("name1") String name,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView("Welcome");

		Date date = new Date();
		long logoutTime = date.getTime();
		long loginTime = 0;
		LoginDetails userDetail = new LoginDetails();
		List<UserDetails> userDetails = userDetail.retrieving(name);
		if (!userDetails.isEmpty()) {
			for (UserDetails user : userDetails) {
				loginTime = user.getLoginTime();
				
				break;
			}
		}
		if(logoutTime!=0) {
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
				
				UserDetails userUpdateDetail = new UserDetails(name.toLowerCase(),loginTime, logoutTime);
				pm.makePersistent(userUpdateDetail);
			}
			finally{
				pm.close();
				
			}
		}
		request.getSession().setAttribute("isLoggedIn", false);
		return model;

	}

	@RequestMapping("/MailSend")
	public ModelAndView sendMail(@RequestParam(value = "name") String userName,
			@RequestParam(value = "email") String userEmail,
			@RequestParam(value = "msg") String msgBody) {
		ModelAndView model = new ModelAndView("MailSent");
		Properties prop = new Properties();
		Session session = Session.getDefaultInstance(prop, null);
		try {
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(
					"vignesh.kandavelu@a-cti.com"));
			mimeMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress("vignesh.kandavelu@a-cti.com",
							"Mr./Ms. " + userName));
			mimeMessage.setSubject("Message from BMI calculations app");
			mimeMessage.setText("Name :: " + userName + "\nEmail-id :: "
					+ userEmail + "\n\nMessage body ::\n" + msgBody);
			Transport.send(mimeMessage);
			System.out.println("Checking mail!!!!!!!!!.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return model;
	}

}