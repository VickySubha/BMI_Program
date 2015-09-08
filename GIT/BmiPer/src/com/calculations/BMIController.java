package com.calculations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.valuepack.ValueProvider;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class BMIController 
{
	
	@RequestMapping("/")
	public ModelAndView welcomeLocator()
	{
		ModelAndView model=	new ModelAndView("Welcome");
		return model;
	}
	
	@RequestMapping("/Index")
	public ModelAndView enterIndex()         
	{
		ModelAndView model=new ModelAndView("Index");
		return model;
	}
	
	@RequestMapping("/StandardBMICalculator")
	public ModelAndView standardBmiCalculator(@RequestParam("feet") int feet,@RequestParam("inch") int inch,@RequestParam("weight") float weight)
	{
		ModelAndView model=new ModelAndView("BMIResult");
		ValueProvider provider=new ValueProvider();
		Float bmi;
		int height=((feet*12)+inch);
		weight=weight*2.2f;
		bmi=((float)weight/(height*height))*provider.getValue();
		model.addObject("bmi",bmi);
		return model;
	}
	
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ModelAndView oauthsuccess(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {
		response.setContentType("text/html");
		
		String Authorised_code = request.getParameter("code");
		// response.getWriter().println("Authorization code is" + Authorised_code);
		String client_id = "659856324305-8hi9lh0qk0en804b1odfcao3ii9fgn42.apps.googleusercontent.com";
		String client_secret = "GJscoH0vjMUrcGZj0TjhOwNC";
		String redirect_uri = "http://1-dot-oauthmvcc-1045.appspot.com/log";

		String grant_type = "authorization_code";

		if (Authorised_code != null) {
			ModelAndView model = new ModelAndView("Index");
			request.getSession().setAttribute("isLoggedIn", true);
			URL u = new URL(
					"https://www.googleapis.com/oauth2/v3/token?client_id="
							+ client_id + "&client_secret=" + client_secret
							+ "&redirect_uri=" + redirect_uri
							+ "&grant_type="+grant_type+"&code=" +Authorised_code);
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
				// response.getWriter().println("hello");
				// response.getWriter().println(content1);
				r.close();

				// response.getWriter().println(con.getResponseCode());
				

				JsonObject obj1 = (JsonObject) new JsonParser().parse(content1);
				//String x=obj1.getAsString();
				
				String name = obj1.get("name").getAsString();
				//System.out.println("Image URL:\t" + mePerson.getImage().getUrl());
				String nam = obj1.get("picture").getAsString();
				
				String imageurl="";
				if(nam.equals("https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg"))
				{
					imageurl="http://images.fineartamerica.com/images/artworkimages/mediumlarge/1/single-yellow-flower-nell-werner.jpg";
					
				}
				else
				{
				imageurl=nam;
				}
				request.setAttribute("name1", name);
				request.setAttribute("name2", imageurl);
				
				//request.setAttribute("name3", x);
				request.getRequestDispatcher("Index.jsp").forward(
						request, response);

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
		return null;

	}

	@RequestMapping("/MetricBMICalculator")
	public ModelAndView metricBmiCalculator(@RequestParam("height") float height,@RequestParam("weight") int weight)
	{
		ModelAndView model=new ModelAndView("BMIResult");
		Float bmi;
		bmi=((float)weight/(height*height));
		model.addObject("bmi",bmi);
		return model;
	}
	
	@RequestMapping("/HeightCalculator")
	public ModelAndView heightCalculator(@RequestParam("weight") int weight,@RequestParam("bmi") float bmi)
	{
		ModelAndView model=new ModelAndView("HeightResult");
		Float height;
		height=(float)Math.sqrt((weight/bmi));
		model.addObject("height",height);
		return model;
	}
	
	@RequestMapping("/WeightCalculator")
	public ModelAndView weightCalculator(@RequestParam("height") float height,@RequestParam("bmi") float bmi)
	{
		ModelAndView model=new ModelAndView("WeightResult");
		Float weight;
		weight=(float)(bmi*(height*height));
		model.addObject("weight",weight);
		return model;
	}
	
	@RequestMapping(value="/ValidateLogin", method=RequestMethod.POST)
	public ModelAndView validateLogin(@RequestParam("uName") String name,@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		ModelAndView model;
		BMIModel bmiModel=new BMIModel();
		if(bmiModel.checkUser(name, password))
		{
			model=new ModelAndView("Index");
			request.getSession().setAttribute("isLoggedIn", true);
			String imageurl="http://images.fineartamerica.com/images/artworkimages/mediumlarge/1/single-yellow-flower-nell-werner.jpg";
			request.setAttribute("name", name);
			request.setAttribute("name2", imageurl);
			request.getRequestDispatcher("Index.jsp").forward(
					request, response);
		}
		else
		{
			model=new ModelAndView("WrongEntry");
		}
		
		return model;
	}
	
	@RequestMapping(value="/AddUsers", method=RequestMethod.POST)
	public ModelAndView addUsers(@RequestParam("uName") String name,@RequestParam("password") String password,HttpServletRequest request)
	{
		ModelAndView model;
		if((name!=null)&&(name!=""))
		{ 
			model=new ModelAndView("Success");
			request.getSession().setAttribute("isLoggedIn", true);
			BMIModel bmiModel=new BMIModel();
			bmiModel.addUsers(name, password);
		}
		else
			model= new ModelAndView("BlankEntries");
		return model;
	}
		
	@RequestMapping("/Logout")
	public ModelAndView logout(HttpServletRequest request)
	{
		ModelAndView model=new ModelAndView("Welcome");
		request.getSession().setAttribute("isLoggedIn", false);
		return model;	
	}
	
	@RequestMapping("/MailSend")
	public ModelAndView sendMail(@RequestParam(value="name") String userName,@RequestParam(value="email") String userEmail,@RequestParam(value="msg") String msgBody)
	{
		ModelAndView model=new ModelAndView("MailSent");
		Properties prop = new Properties();
	    Session session = Session.getDefaultInstance(prop,null);
	    try{    
	        Message mimeMessage = new MimeMessage(session);
	        mimeMessage.setFrom(new InternetAddress("vignesh.kandavelu@a-cti.com"));
	        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("vignesh.kandavelu@a-cti.com", "Mr./Ms. "+userName));
	        mimeMessage.setSubject("Message from BMI calculations app");
	        mimeMessage.setText("Name :: "+userName+"\nEmail-id :: "+userEmail+"\n\nMessage body ::\n"+msgBody);
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