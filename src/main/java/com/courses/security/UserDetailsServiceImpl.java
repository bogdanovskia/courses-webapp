package com.courses.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.courses.model.User;
import com.courses.model.UserRole;
import com.courses.persistence.UserRepository;

public class UserDetailsServiceImpl<T extends User> implements UserDetailsService {
	@Autowired
	private UserRepository<T> userRepository;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		System.out.println("///////////");
		System.out.println(user.getFirstName());
		System.out.println("///////////");

		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

		Set<UserRole> roles = userRepository.getRoles(user);

		System.out.println("///////////");
		for (UserRole r : roles) {
			System.out.println(r.getRole());
		}
		System.out.println("///////////");

		for (UserRole role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}
}
