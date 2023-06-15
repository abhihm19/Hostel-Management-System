package in.cdac.hms.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.cdac.hms.exception.ResourceNotFoundException;
import in.cdac.hms.model.User;
import in.cdac.hms.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getPassword(),
				user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList()));
	}
}