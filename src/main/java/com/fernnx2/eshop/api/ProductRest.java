package com.fernnx2.eshop.api;

import com.fernnx2.eshop.models.Product;
import com.fernnx2.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "product")
public class ProductRest {

    @Autowired
    ProductService ps;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            return new ResponseEntity<>(this.ps.findAllProduct(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
        try{
            Optional<Product> product = this.ps.findProductById(id);
            if(product.isPresent()){
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        try{
            return new ResponseEntity<>(this.ps.saveOrUpdate(product), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id){
        try{
            this.ps.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
