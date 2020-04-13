package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

@Repository

public interface UserRepository extends CrudRepository<User, Integer>{
	
	 @Query(value = "SELECT * FROM users u WHERE u.login = ?1 and u.password = ?2 and u.imported = false", nativeQuery=true)
	 User findUser(String username, String password);

}
