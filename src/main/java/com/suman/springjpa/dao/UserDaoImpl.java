package com.suman.springjpa.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.suman.springjpa.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
 

	public User findById(Integer id) {
		 User user = getByKey(id);   
		return user;
	}
 
         
    public List<User> findAllUsers() {   	
    	
    	TypedQuery<User> tq= getTypedQuery("findAllUsers");  
    	
        return tq.getResultList();
    }
 
    public void save(User user) {
        persist(user);
    }
 
    public void delete(Integer id) {
        
        delete(id);
    }


    
}
