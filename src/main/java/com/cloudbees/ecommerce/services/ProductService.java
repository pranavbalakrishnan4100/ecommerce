package com.cloudbees.ecommerce.services;

import com.cloudbees.ecommerce.entities.Product;
import com.cloudbees.ecommerce.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public static Product getProduct(Long id){
        return ProductRepository.findById(id);
    }

    public static Product postProduct(Product product) throws Exception {
        if(ProductRepository.findByName(product.getName())==null) {
            ProductRepository.idCounter++;
            product.setId(ProductRepository.idCounter);
            return ProductRepository.save(product);
        }else{
            throw new Exception("Name is not unique!");
        }
    }

    public static Product putProduct(Long id, Product newProduct){
        newProduct.setId(id);
        return ProductRepository.updateById(id, newProduct);
    }

    public static boolean deleteProduct(Long id){
        return ProductRepository.deleteById(id);
    }

    public static Product taxProduct(int value, long id){
        Product product=ProductService.getProduct(id);
        if(product!=null){
            double taxAmount = product.getPrice() * (value/100.0);
            product.setPrice(product.getPrice()+taxAmount);
            return product;
        }
        return null;
    }

    public static Product discountProduct(int value, long id){
        Product product=ProductService.getProduct(id);
        if(product!=null){
            double price=product.getPrice();
            double discountAmount = price * (value/100.0);
            product.setPrice(price-discountAmount);
            return product;
        }
        return null;
    }
}
