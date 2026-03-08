package com.RedisRabbitMQ.AuthApplication.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RedisRabbitMQ.AuthApplication.Entiry.User;
import com.RedisRabbitMQ.AuthApplication.Exception.UserEmailNotFoundException;
import com.RedisRabbitMQ.AuthApplication.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> 
				new UserEmailNotFoundException("Email and Password not found"));
		return new CustomUserDetail(user);
	}

}
