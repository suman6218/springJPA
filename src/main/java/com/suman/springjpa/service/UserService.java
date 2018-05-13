package com.suman.springjpa.service;

import java.util.List;

import com.suman.springjpa.entity.User;

public interface UserService {
	
	User findById(Integer id);    
    
    void save(User user);
     
    void delete(Integer id);
     
    List<User> findAllUsers();

}
