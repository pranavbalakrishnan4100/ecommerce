package com.cloudbees.ecommerce.repositories;

import com.cloudbees.ecommerce.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public static long idCounter=0;
    public static List<Product> productList= new ArrayList<>();

    public static Product findById(Long id){
        for(Product p:productList){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public static Product findByName(String name){
        for(Product p:productList){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public static Product save(Product product){
        productList.add(product);
        return product;
    }

    public static Product updateById(long id, Product newProduct){
        for(int i=0;i<productList.size();i++) {
            if (productList.get(i).getId().equals(id)) {
                productList.set(i, newProduct);
                return productList.get(i);
            }
        }
        return null;
    }

    public static boolean deleteById(long id){
        for(Product p:productList){
            if(p.getId().equals(id)){
               return productList.remove(p);
            }
        }
        return false;
    }

}
