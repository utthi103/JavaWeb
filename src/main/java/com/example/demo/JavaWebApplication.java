package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JavaWebApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(JavaWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		   String sql = "INSERT INTO admin ( admin_fullname,admin_username,admin_password,admin_email,admin_phone,admin_address  )"
//	        		+ " VALUES ( ?,?,?,?,?,?)";
//		   int result = jdbcTemplate.update(sql,"sd","z","ád","s","sa","d");
//		   if(result>0) {
//			   System.out.println("insert a row");
//		   }
		
	}

}
