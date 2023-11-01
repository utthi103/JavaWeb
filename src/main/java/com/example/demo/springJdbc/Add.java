//package com.example.demo.springJdbc;
//
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.example.demo.Model.Employee;
//
//public class Add {
//
//    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
//
//    public void Add(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public void addUser(Employee user) {
//        String sql = "INSERT INTO admin ( admin_fullname,admin_username,admin_password,admin_email,admin_phone,admin_address  )"
//        		+ " VALUES ( 2,3,4,5,6,7)";
//        jdbcTemplate.update(sql,  user.getAdmin_fullname(), user.getAdmin_username(),
//        user.getAdmin_password(), user.getAdmin_email(),user.getAdmin_phone(), user.getAdmin_address());
//    }
//   
//}
// 