package com.cloudbees.ecommerce;

import com.cloudbees.ecommerce.entities.Product;
import com.cloudbees.ecommerce.repositories.ProductRepository;
import com.cloudbees.ecommerce.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class EcommerceApplicationTests {

	@AfterEach
	void after() throws Exception {
		for(int i=0; i<ProductRepository.productList.size();i++){
			ProductRepository.productList.remove(i);
		}
	}

	@Test
	void add() throws Exception {
		Product p1=new Product("Product1", "Description1", 10.99, 5);
		Product savedProduct = ProductService.postProduct(p1);

		assertNotNull(savedProduct);
		assertEquals(p1.getName(), savedProduct.getName());
		assertEquals(p1.getDescription(), savedProduct.getDescription());
		assertEquals(p1.getPrice(), savedProduct.getPrice());
		assertEquals(p1.getQuantity(), savedProduct.getQuantity());
	}


	@Test
	void get() throws Exception {
		Product p1=new Product("Product1", "Description1", 10.99, 5);
		long productId=ProductService.postProduct(p1).getId();

		Product retrievedProduct = ProductService.getProduct(productId);

		assertNotNull(retrievedProduct);
		assertEquals(p1.getName(), retrievedProduct.getName());
		assertEquals(p1.getDescription(), retrievedProduct.getDescription());
		assertEquals(p1.getPrice(), retrievedProduct.getPrice());
		assertEquals(p1.getQuantity(), retrievedProduct.getQuantity());
	}

	@Test
	void update() throws Exception {
		Product p1=new Product("Product1", "Description1", 10.99, 5);
		Product savedProduct = ProductService.postProduct(p1);

		long productId = savedProduct.getId();
		Product p2=new Product("Product1", "New Description", 10.99, 5);
		p2.setId(productId);
		Product updatedProduct = ProductService.putProduct(productId, p2);

		assertNotNull(updatedProduct);
		assertEquals(updatedProduct.getName(), p2.getName());
		assertEquals(updatedProduct.getDescription(), p2.getDescription());
		assertEquals(updatedProduct.getPrice(), p2.getPrice());
		assertEquals(updatedProduct.getQuantity(), p2.getQuantity());
	}

	@Test
	void discount() throws Exception {
		Product p1=new Product("Product1", "Description1", 10.99, 5);
		Product savedProduct = ProductService.postProduct(p1);
		long productId = savedProduct.getId();
		double originalPrice=p1.getPrice();

		double discAmount=(originalPrice)*(10/100.0);
		Product updatedProduct=ProductService.discountProduct(10, productId);

		assertEquals(originalPrice-discAmount, updatedProduct.getPrice());
	}

	@Test
	void tax() throws Exception {
		Product p1=new Product("Product1", "Description1", 10.99, 5);
		Product savedProduct = ProductService.postProduct(p1);
		long productId = savedProduct.getId();
		double originalPrice=p1.getPrice();

		double taxAmount=(originalPrice)*(10/100.0);
		Product updatedProduct=ProductService.taxProduct(10, productId);

		assertEquals(originalPrice+taxAmount, updatedProduct.getPrice());
	}

	@Test
	void delete() throws Exception {
		Product p1=new Product("Product1", "Description1", 10.99, 5);
		Product savedProduct = ProductService.postProduct(p1);
		long productId = savedProduct.getId();
		ProductService.deleteProduct(productId);
		assertEquals(0, ProductRepository.productList.size());
	}
}
