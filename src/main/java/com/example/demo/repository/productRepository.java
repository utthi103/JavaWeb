package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.productModel;

import java.util.List;

@Repository
public class productRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public productRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<productModel> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            productModel product = new productModel();
            product.setId_product(rs.getInt("id_product"));
            product.setName_product(rs.getString("name_product"));
            product.setName_category(rs.getString("name_category"));
            product.setPrice_product(rs.getFloat("price_product"));
            product.setCount_product(rs.getInt("count_product"));
            product.setImage1(rs.getString("image1"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setDecription_product(rs.getString("decription_product"));
            product.setDecription(rs.getString("decription"));
            product.setSale_product(rs.getInt("sale_product"));
            product.setOutstand_product(rs.getString("outstand_product"));
            product.setDate_product(rs.getDate("date_product"));
            product.setPrice_sale(rs.getFloat("price_sale"));

            
            return product;
        });
    }
}