package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.user;
import com.example.demo.repository.userRepository;

@Service
public class userService {
	private userRepository userRepository;
	
	@Autowired
    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

	   public List<user> getAllBlog() {
	        return userRepository.findAll();
	    }
	    
	   public void deleteUser(int idUser) {
			userRepository.deleteById(idUser);;
		}
	   
	   public user isValidUser(String username, String password) {
		    user user = userRepository.findByAccountUserAndPassUser(username, password);
		    return user;
		}
	   
	   
}
