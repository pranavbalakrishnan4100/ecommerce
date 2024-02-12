package com.cloudbees.ecommerce;

import com.cloudbees.ecommerce.entities.Product;
import com.cloudbees.ecommerce.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.Scanner;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class EcommerceApplication {
	public static Scanner sc = new Scanner((System.in));

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);

		boolean running = true;
		while (running) {
			System.out.println("Cloudbees ecommerce");
			System.out.println("1. Add Product");
			System.out.println("2. View Product");
			System.out.println("3. Update Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Add Tax");
			System.out.println("6. Add Discount");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
				case 1:
					addProduct();
					break;
				case 2:
					viewProduct();
					break;
				case 3:
					updateProduct();
					break;
				case 4:
					deleteProduct();
					break;
				case 5:
					addTax();
					break;
				case 6:
					addDiscount();
					break;
				case 7:
					running = false;
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
		sc.close();
	}

	private static void addProduct() {
		System.out.print("Enter product name: ");
		String name = sc.nextLine();
		System.out.print("Enter product description: ");
		String description = sc.nextLine();
		System.out.print("Enter product price: ");
		double price = sc.nextDouble();
		System.out.print("Enter product quantity: ");
		int quantity = sc.nextInt();
		Product product = new Product(name, description, price, quantity);
		try {
			Product response=ProductService.postProduct(product);
			System.out.println(response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Product added successfully.");
	}

	private static void viewProduct() {
		System.out.print("Enter product ID: ");
		long id = sc.nextLong();
		Product product = ProductService.getProduct(id);
		if (product != null) {
			System.out.println("Product Details:");
			System.out.println(product);
		} else {
			System.out.println("Product not found.");
		}
	}

	private static void updateProduct() {
		System.out.print("Enter product ID: ");
		long id = sc.nextLong();
		sc.nextLine();
		System.out.print("Enter new product name: ");
		String name = sc.nextLine();
		System.out.print("Enter new product description: ");
		String description = sc.nextLine();
		System.out.print("Enter new product price: ");
		double price = sc.nextDouble();
		System.out.print("Enter new product quantity: ");
		int quantity = sc.nextInt();
		Product product = new Product(name, description, price, quantity);
		Product response=ProductService.putProduct(id, product);
		if(response!=null){
			System.out.println("Product updated successfully.");
			System.out.println(response);
		}else{
			System.out.println("Product not found.");
		}
	}

	private static void addDiscount() {
		System.out.print("Enter product ID: ");
		long id = sc.nextLong();
		sc.nextLine();
		System.out.print("Enter discount rate:");
		int discount = sc.nextInt();
		Product response=ProductService.discountProduct(discount, id);
		if(response!=null){
			System.out.println("Discount applied successfully.");
			System.out.println(response);
		}else{
			System.out.println("Product not found.");
		}
	}

	private static void addTax() {
		System.out.print("Enter product ID: ");
		long id = sc.nextLong();
		sc.nextLine();
		System.out.print("Enter tax rate: ");
		int tax = sc.nextInt();
		Product response=ProductService.taxProduct(tax, id);
		if(response!=null){
			System.out.println("Tax applied successfully.");
			System.out.println(response);
		}else{
			System.out.println("Product not found.");
		}
	}

	private static void deleteProduct() {
		System.out.print("Enter product ID: ");
		long id = sc.nextLong();
		if(ProductService.deleteProduct(id)){
			System.out.println("Product deleted successfully.");
		}else{
			System.out.println("Product not found");
		}

	}
}

