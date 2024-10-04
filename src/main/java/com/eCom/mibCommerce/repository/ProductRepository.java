package com.eCom.mibCommerce.repository;

import com.eCom.mibCommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
