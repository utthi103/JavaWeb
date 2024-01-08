package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.category;
import com.example.demo.Entity.order;
import com.example.demo.Entity.product;
import com.example.demo.repository.categoryRepository;
import com.example.demo.repository.productRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class productService {
    private productRepository productRepository;
    private categoryRepository categoryRepository;


    @Autowired
    public productService(productRepository productRepository, categoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<product> getAllProducts() {
        return productRepository.findAll();
    }


	public Page<product> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 6);
		return this.productRepository.findAll(pageable);
	}
//	detail 
	public product getProductById(int productId) {
	    Optional<product> productOptional = productRepository.findById(productId);
	    return productOptional.orElse(null);
	}
	
	public List<product> getHighlightedProducts() {
        return productRepository.findByOutstandProduct("có");
    }
	public List<product> newProduct() {
        return productRepository.findTop4ByOrderByDateProductDesc();
    }
	
	 public void updateCount(int id_product, int qty) {
		 product product = productRepository.findById_product(id_product);
	        if (product != null) {
	            product.setCount_product(product.getCount_product() - qty);
	            productRepository.save(product);
	        }
	    }
	 
		public void deleteProduct(int idProduct) {
			productRepository.deleteById(idProduct);;
		}
	 
	    public void updateProduct(
	    		int id_product,
	    		String name_product,
	    		String name_category,
	    		Float price_product,
	    		int count_product,
	    		String image1,
	    		String image2,
	    		String image3,
	    		String decription_product,
	    		String decription,
	    		int sale_product,
	    		String outstand_product,
	    		Date date_product,
	    		Float price_sale
	    		) {
	        Optional<product> optionalProduct = productRepository.findById(id_product);
	        if (optionalProduct.isPresent()) {
	            product product = optionalProduct.get();
	            product.setName_product(name_product);
	            category categoryEntity = categoryRepository.findByNameCategory(name_category);
	            if (categoryEntity == null) {
	                // Nếu name_category chưa tồn tại, hãy tạo một đối tượng mới và lưu vào cơ sở dữ liệu
	                categoryEntity = new category();
	                categoryEntity.setName_category(name_category);
	                categoryRepository.save(categoryEntity);
	            }

	            product.setCategoryEntity(categoryEntity);
	            
	            // Cập nhật các thông tin khác của sản phẩm
	            product.setPrice_product(price_product);
	            product.setCount_product(count_product);
	            product.setImage1(image1);
	            product.setImage2(image2);
	            product.setImage3(image3);
	            product.setDecription_product(decription_product);
	            product.setDecription(decription);
	            product.setSale_product(sale_product);
	            product.setOutstandProduct(outstand_product);
	            product.setDateProduct(date_product);
	            product.setPrice_product(price_product);
	            categoryRepository.save(categoryEntity);
	            productRepository.save(product);  
	            } else {
	            // Xử lý khi không tìm thấy danh mục
	        }
	    }
	    
	    public void addproduct(product product, 
	    		String name_product,
	    		String name_category,
	    		Float price_product,
	    		int count_product,
	    		String image1,
	    		String image2,
	    		String image3,
	    		String decription_product,
	    		String decription,
	    		int sale_product,
	    		String outstand_product,
	    		Date date_product,
	    		Float price_sale
	    		) {
	    	 product.setName_product(name_product);
	    	  category categoryEntity = categoryRepository.findByNameCategory(name_category);
	            if (categoryEntity == null) {
	                // Nếu name_category chưa tồn tại, hãy tạo một đối tượng mới và lưu vào cơ sở dữ liệu
	                categoryEntity = new category();
	                categoryEntity.setName_category(name_category);
	                categoryRepository.save(categoryEntity);
	            }

	            product.setCategoryEntity(categoryEntity);
	            product.setPrice_product(price_product);
	            product.setCount_product(count_product);
	            product.setImage1(image1);
	            product.setImage2(image2);
	            product.setImage3(image3);
	            product.setDecription_product(decription_product);
	            product.setDecription(decription);
	            product.setSale_product(sale_product);
	            product.setOutstandProduct(outstand_product);
	            product.setDateProduct(date_product);
	            product.setPrice_sale(price_sale);
		        productRepository.save(product);
	    }
	    public List<product> searchProducts(String nameProduct) {
	        return productRepository.findProductsByName(nameProduct);
	    }
	    public List<product> searchNameCate(String nameCate) {
	        return productRepository.findByCategoryEntity_NameCategoryContaining(nameCate);
	    }
	    
	    public List<product> searchPrice(Float minPrice, Float maxPrice) {
	        return productRepository.findProductsByPriceSaleRange(minPrice,maxPrice );
	    }
}