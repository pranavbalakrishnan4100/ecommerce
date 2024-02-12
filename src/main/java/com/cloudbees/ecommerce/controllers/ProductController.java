package com.cloudbees.ecommerce.controllers;

import com.cloudbees.ecommerce.entities.Product;
import com.cloudbees.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/products")
public class ProductController {

    @GetMapping(path="/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        Product product=ProductService.getProduct(id);
        if(product!=null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product does not exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Product product){
        try{
            Product newProduct=ProductService.postProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody Product product){
        Product newProduct=ProductService.putProduct(id, product);
        if(newProduct!=null){
            return new ResponseEntity<>(newProduct, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product does not exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path="/{id}/discount")
    public ResponseEntity<Object> putDiscount(@PathVariable Long id, @RequestParam int value){
        Product product=ProductService.discountProduct(value, id);
        if(product!=null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>("Product does not exist.", HttpStatus.NOT_FOUND);
    }

    @PutMapping(path="/{id}/tax")
    public ResponseEntity<Object> putTax(@PathVariable Long id, @RequestParam int value){
        Product product=ProductService.taxProduct(value, id);
        if(product!=null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>("Product does not exist.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        if(ProductService.deleteProduct(id)){
            return new ResponseEntity<>("Product deleted.",HttpStatus.OK);
        }
        return new ResponseEntity<>("Product does not exist.",HttpStatus.NOT_FOUND);
    }
}
