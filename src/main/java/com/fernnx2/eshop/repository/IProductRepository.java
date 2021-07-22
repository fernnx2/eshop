package com.fernnx2.eshop.repository;

import com.fernnx2.eshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
