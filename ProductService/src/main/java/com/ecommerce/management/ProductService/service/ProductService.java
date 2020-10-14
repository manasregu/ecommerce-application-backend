package com.ecommerce.management.ProductService.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ecommerce.management.ProductService.entity.Product;
import com.ecommerce.management.ProductService.repository.ProductRepository;


@Component
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//As given in UseCase detail, by default product is sorted by price in ascending order with distinct elements
	public List<Product> getProductForCart(){
			return productRepository.findAll(Sort.by("price").ascending()).
					stream().distinct().collect(Collectors.toList());
	}
	

	public List<Product> getSortedProductDetails(String sortParam) {
		if(sortParam.equalsIgnoreCase("name")) {
			return productRepository.findAll(Sort.by("name").descending());
		}else if(sortParam.equalsIgnoreCase("price")) {
			return productRepository.findAll(Sort.by("price").descending());
		}else if((sortParam).equals(null)) {
			return productRepository.findAll();
		}
		return productRepository.findAll();
	}


	public Optional<Product> getById(int id) {
		return productRepository.findById(id);
	}


	public List<Product> getByProductName(String product) {
		// TODO Auto-generated method stub
		return productRepository.findByName(product);
	}

	
}
