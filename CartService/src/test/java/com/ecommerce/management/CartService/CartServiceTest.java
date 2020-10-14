package com.ecommerce.management.CartService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.management.CartService.Model.Cart;
import com.ecommerce.management.CartService.Service.CartService;
import com.ecommerce.management.CartService.dao.CartRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CartService.class)
public class CartServiceTest {

	@MockBean
	private CartRepository cartRepo;;
	
	@Autowired
	private CartService cartService;
	
	@Test
	public void getCartItemByUid() {
		cartService.getCartItems(899);
		Mockito.verify(cartRepo, Mockito.times(1)).findByUid(899);
	}
	
	@Test
	public void removeItemFromCartByUserId() {
		cartService.removeItemFromCartByUserId(1);
		Mockito.verify(cartRepo, Mockito.times(1)).deleteById(899);
	}
	@Test
	public void updatePriceOfItem() {
		cartService.updatePriceOfItem(1,"Pant",500);
		Cart entity = new Cart();
		entity.setUid(1);
		entity.setName("Pant");
		entity.setPrice((float) 500.00);
		Mockito.verify(cartRepo, Mockito.times(1)).save(entity);
	}
	
}
