package com.abhi.atm.controller.registrationController;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.atm.dao.entity.Role;
import com.abhi.atm.dao.entity.User;
import com.abhi.atm.service.userMgmtService.UserMgmtService;

import net.sf.json.JSONObject;

/**
 * @author Abhishek Patel M N Jan 17, 2018
 */
@RestController
public class RegistrationController {

	@Autowired
	UserMgmtService userMgmtService;

	@PostMapping(value = "/registerUser")
	public ResponseEntity<String> registerUser(HttpServletRequest request) {
		JSONObject jsonResponse = new JSONObject();
		String data = "";
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			user.setEmail(email);

			Role role = new Role();
			role.setRoleName("USER");
			user.setRole(role);
			user.setCreatedDate(new Date());

			userMgmtService.addUser(user);
			data = "User " + user.getUserName() + " registered successfully";
			jsonResponse.put("data", data);
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.OK);
		} catch (Exception e) {
			jsonResponse.put("data", e.getMessage());
			return new ResponseEntity<String>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
