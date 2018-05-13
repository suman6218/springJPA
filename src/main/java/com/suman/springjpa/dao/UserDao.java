package com.suman.springjpa.dao;

import java.util.List;

import com.suman.springjpa.entity.User;

public interface UserDao {
	
	User findById(Integer id);    
     
    void save(User user);
     
    void delete(Integer id);
     
    List<User> findAllUsers();
    

}
