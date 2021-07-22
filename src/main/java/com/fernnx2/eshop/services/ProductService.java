package com.fernnx2.eshop.services;

import com.fernnx2.eshop.models.Product;
import com.fernnx2.eshop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    IProductRepository ipr;

    public List<Product> findAllProduct(){
        List<Product> products = new ArrayList<Product>();
        this.ipr.findAll().forEach(product -> products.add(product));
        return products;
    }

    public Product saveOrUpdate(Product product){
        return  this.ipr.save(product);
    }

    public Optional<Product> findProductById(int id){
        return this.ipr.findById(id);
    }

    public boolean deleteProduct(int id){
           this.ipr.deleteById(id);
           return true;
    }
}
