package com.springmvcjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvcjpa.model.UserInfo;
import com.springmvcjpa.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/getUsers")
	public String getAllUsers(Model model) {
		model.addAttribute("user", new UserInfo());
		model.addAttribute("listUsers", this.userService.getAllUsers());
		return "user";
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public @ResponseBody UserInfo getUser(@PathVariable String userID) {
		return userService.getById(userID);
	}

	@RequestMapping(value = "/getUserByLastName/{lastName}", method = RequestMethod.GET)
	public List<UserInfo> getUserByLastName(@PathVariable String lastName) {
		return userService.findByLastName(lastName);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") UserInfo userInfo) {
		this.userService.addUser(userInfo);

		return "redirect:/users";
	}

	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("userID") String userID) {
		this.userService.deleteUser(userID);
		return "redirect:/users";
	}

	@RequestMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("userID") String userID, Model model) {
		model.addAttribute("user", this.userService.getById(userID));
		model.addAttribute("listUsers", this.userService.getAllUsers());
		return "user";
	}
}