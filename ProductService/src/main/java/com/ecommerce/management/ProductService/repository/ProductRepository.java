package com.ecommerce.management.ProductService.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.management.ProductService.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByName(String name);
	
	
	
	
}
