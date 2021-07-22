package com.fernnx2.eshop.services;

import com.fernnx2.eshop.models.Product;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class ProductServiceTest {

    @Autowired
    ProductService ps;

    static Product p = new Product();


    @BeforeClass
    public static void beforeClassTest(){
        p.setName("Huawei");
        p.setPriceBuy(200.00);
        p.setPriceSell(300.00);
        p.setStock(5);
        p.setDescription("Celular Smartphone");

    }

    @Test
    public void findAll_thenAccept(){
        Product y = new Product();
        y.setName("Huawei");
        y.setPriceBuy(200.00);
        y.setPriceSell(300.00);
        y.setStock(5);
        y.setDescription("Celular Smartphone");
        ps.saveOrUpdate(y);
        Assert.assertEquals(ps.findAllProduct().stream().count(),1);
    }

    @Test
    public void saveOrUpdate_thenAccept(){
        Product x = ps.saveOrUpdate(p);
        Assert.assertEquals(x, p);
    }

    @Test
    public void findProductById_thenAccept(){
        Assert.assertEquals(this.ps.findProductById(p.getId()).get(), p);
    }

    @Test
    public void deleteProduct_thenAccept(){
        Assert.assertTrue(this.ps.deleteProduct(1));
    }



}
