package com.suman.springjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suman.springjpa.dao.UserDao;
import com.suman.springjpa.entity.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserDao dao;

	public User findById(Integer id) {
		return dao.findById(id);
	}

	public void save(User user) {
		dao.save(user);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

}
