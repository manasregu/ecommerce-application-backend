package com.ecommerce.management.CartService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.management.CartService.Model.Cart;
import com.ecommerce.management.CartService.dao.CartRepository;


@Component
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;

	public List<Cart> getCartItems(int id) {
		return cartRepo.findByUid(id);
	}

	public void addItemsTocart(List<Cart> cartItems) {
		 cartRepo.saveAll(cartItems);
		
	}

	public void removeItemFromCartByUserId(int uid) {
		List<Cart> tempList = cartRepo.findByUid(uid);
		tempList.stream().forEach(item -> cartRepo.deleteById(item.getCid()));
	}

	public void updatePriceOfItem(int uid, String product, float price) {
		List<Cart> tempList = cartRepo.findByUid(uid);
		Cart c = new Cart();
		tempList.stream().forEach(item -> {
			if(item.getName().equalsIgnoreCase(product)) {
				c.setUid(item.getUid());
				c.setPid(item.getPid());
				c.setCid(item.getCid());
				c.setName(item.getName());
				c.setPrice(price);
				cartRepo.save(c);
			}
		});
	}

	

}
