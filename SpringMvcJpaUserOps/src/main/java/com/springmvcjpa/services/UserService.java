package com.springmvcjpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvcjpa.model.UserInfo;
import com.springmvcjpa.repository.UserInfoDAO;

@Service
public class UserService {
	@Autowired
	private UserInfoDAO userInfoDAO;

	public void addUser(UserInfo userInfo) {
		userInfoDAO.save(userInfo);
	}

	@Transactional
	public boolean updateUser(UserInfo userInfo) {
		return userInfoDAO.save(userInfo) != null;
	}

	public List<UserInfo> getAllUsers() {
		List<UserInfo> userInfoList = new ArrayList<>();
		userInfoDAO.findAll().forEach(userInfoList::add);
		return userInfoList;
	}

	@Transactional
	public UserInfo getById(String userID) {
		// return userInfoDAO.findOne(id);
		return null;
	}

	@Transactional
	public List<UserInfo> findByLastName(String lastName) {
		return userInfoDAO.findByLastName(lastName);
	}

	@Transactional
	public void deleteUser(String userID) {
		// userInfoDAO.delete(userID);
	}
}