package com.ecommerce.management.CartService.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.management.CartService.Model.Cart;
import com.ecommerce.management.CartService.Service.CartService;

@RestController
@RequestMapping("/cart")

public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private RestTemplate restTemplate;

	// Getting all the products that the user added to cart by passing User Id as
	// parameter
	@GetMapping("/showCartItems")
	public List<Cart> showCartItemsById(@RequestParam int uid) {
		return cartService.getCartItems(uid);
	}

	// Adding a list of products that the user chosed in ProductService to cart DB
	@RequestMapping("/addToCart")
	public String addAllItemsTocart(@RequestParam int uid) {
		int userId = uid;
		List<Cart> items = new ArrayList<Cart>();
		List<Cart> itemsToAdd = new ArrayList<Cart>();
		try {
			ResponseEntity<List<Cart>> claimResponse = restTemplate.exchange(
					"http://product-service/product/showProduct", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Cart>>() {
					});
			if (claimResponse != null && claimResponse.hasBody()) {
				items = claimResponse.getBody();
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		for(int i=0;i<items.size();i++) {
			Cart c = new Cart();
			c.setUid(uid);
			c.setPid(items.get(i).getPid());
			c.setName(items.get(i).getName());
			c.setPrice(items.get(i).getPrice());
			itemsToAdd.add(c);
		}
		cartService.addItemsTocart(itemsToAdd);
		return itemsToAdd.size()+" items added successfully";
	}

	//Updating Price of product being chosed by user
	@RequestMapping("/updateProductPrice")
	public String updateEmployee(@RequestParam int uid,@RequestParam String product, @RequestParam float price) {
		cartService.updatePriceOfItem(uid, product, price);
		return "Updated successfully";
	}

	//Deleting all the products chosen by the user
	@RequestMapping("/remove")
	public String DeleteById(@RequestParam int uid) {
		cartService.removeItemFromCartByUserId(uid);
		return "Deleted succesfully";
	}

	//Placing order by sending an Email regarding Order Placement
	@GetMapping("/placeOrder")
	public String placeOrderForUser(@RequestParam int uid) throws MessagingException {
		// Get list of products chosen by the user with given user id
		List<Cart> itemsToOrder = cartService.getCartItems(uid);
		double totalAmount = itemsToOrder.stream().mapToDouble(item -> item.getPrice()).sum();
		sendEmail(totalAmount,uid);
		return "Order Placed and Email Sent successfully to the user";
	}

	private void sendEmail(double totalAmount, int uid) throws MessagingException {
		String emailId = getEmailId(uid);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Host mailId and password is hard coded
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("manasregu@gmail.com", "Manas@1324");
			}
		});
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Order Placed successfully.... Please Pay Rs." + totalAmount + " as cash on Delivery",
				"text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("manasregu@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
		msg.setSubject("Order placement");
		msg.setContent(multipart, "text/html");
		msg.setSentDate(new Date());

	

		Transport.send(msg);

	}
    
	//Method to get Email id of the user 
	private String getEmailId(int uid) {
		String mailId = null;
		try {
			ResponseEntity<String> claimResponse = restTemplate.exchange("http://user-service/getMail?uid=" + uid,
					HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
					});
			if (claimResponse != null && claimResponse.hasBody()) {
				mailId = claimResponse.getBody();
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return mailId;
	}

}
