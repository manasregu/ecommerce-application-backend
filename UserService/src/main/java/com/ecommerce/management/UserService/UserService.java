package com.ecommerce.management.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user =  repo.findByName(username);
	   if(user == null) {
		   throw new UsernameNotFoundException("Not a Registered User");
	   }
		return new UserPrincipal(user);
	}
	
	public Boolean registerUser(Customer user) {
		repo.save(user);
		return true;
	}
	
	public String getMailId(int uid) {
		return repo.findById(uid).getEmail();
		
	}

	
}
