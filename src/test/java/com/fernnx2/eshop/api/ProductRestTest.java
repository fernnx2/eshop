package com.fernnx2.eshop.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernnx2.eshop.models.Product;
import com.fernnx2.eshop.services.ProductService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductRest.class)
public class ProductRestTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService ps;

    static Product x;
    static Product y;
    static List<Product> productList = new ArrayList<Product>();

    @BeforeClass
    public static void setUp(){
         x = new Product(1,"Huawei",200.00,300.00,5,1,"Celular");
         y = new Product(2,"Iphone",1000.00,1400.00,5,1,"Celular");
         productList.add(x);
         productList.add(y);
    }

    @Test
    public void getAllProduct_thenAccept() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String p1 = objectMapper.writeValueAsString(x);
        String p2 = objectMapper.writeValueAsString(y);
        given(this.ps.findAllProduct()).willReturn(productList);
        this.mvc.perform(get("/product").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" + p1 + "," + p2 + "]"));

    }

    @Test
    public void getProduct_thenAccept() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String p1 = objectMapper.writeValueAsString(x);
        given(this.ps.findProductById(x.getId())).willReturn(productList.stream().findFirst());
        this.mvc.perform(get("/product/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(p1));
    }

    @Test
    public void saveProduct_thenAccept() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String p1 = objectMapper.writeValueAsString(x);
        given(this.ps.saveOrUpdate(x)).willReturn(productList.stream().findFirst().get());
        this.mvc.perform(post("/product")
                .content(p1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(p1));
    }

    @Test
    public void deleteProduct_thenAccept() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String p1 = objectMapper.writeValueAsString(x);
        given(this.ps.deleteProduct(x.getId())).willReturn(true);
        this.mvc.perform(delete("/product/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
