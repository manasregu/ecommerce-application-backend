package com.ecommerce.management.ProductService.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.management.ProductService.entity.Product;
import com.ecommerce.management.ProductService.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	//Assuming that this method returns the list of products chosen by user to be added to cart
	@RequestMapping(value ="/showProduct", method = RequestMethod.GET)
	public List<Product> getProductForCart() {
		return productService.getProductForCart();
	}
 
	//Getting the list of product by passing Product Id
	@RequestMapping("/getProduct")
	public Optional<Product> getProduct(@RequestParam int pid) {
		return productService.getById(pid);
	}

	//Getting the list of product by passing Product name
	@RequestMapping(value ="/searchProduct", method = RequestMethod.GET)
	public List<Product> searchForProduct(@RequestParam String product) { 
		return productService.getByProductName(product);
	}

	//Sorting the list of products based on sort parameter being passed
	@RequestMapping("/sort")
	public List<Product> sortByOption(@RequestParam String sortParam) {
		return productService.getSortedProductDetails(sortParam);

	}



}
