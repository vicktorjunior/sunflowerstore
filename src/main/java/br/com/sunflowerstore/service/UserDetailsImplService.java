package br.com.sunflowerstore.service;


import br.com.sunflowerstore.config.auth.UserImpl;
import br.com.sunflowerstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by rodrigo on 2/21/17.
 */
@Service
public class UserDetailsImplService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsImplService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByUsername(username)
				.map(user -> new UserImpl(
				        user.getUsername(),
                        user.getPassword(),
						user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList()),
                        user)
                ).orElseThrow(() -> new UsernameNotFoundException("couldn't find " + username + "!"));
	}
}