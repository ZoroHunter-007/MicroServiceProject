package com.MicroServiceProject.AuthApplication.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MicroServiceProject.AuthApplication.Entiry.User;
import com.MicroServiceProject.AuthApplication.Exception.UserEmailNotFoundException;
import com.MicroServiceProject.AuthApplication.Repository.UserRepository;

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
