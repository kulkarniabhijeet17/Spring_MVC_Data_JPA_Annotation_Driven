package com.springmvcjpa.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springmvcjpa.model.UserInfo;

public interface UserInfoDAO extends CrudRepository<UserInfo, Long> {
	List<UserInfo> findByUserID(String userID);

	List<UserInfo> findByLastName(String lastName);

	// custom query example and return a stream
	@Query("select firstName, lastName from UserInfo where user_id = :userID")
	Stream<UserInfo> findByEmailReturnStream(@Param("userID") String userID);

	// List<UserInfo> findByDate(Date date);
}