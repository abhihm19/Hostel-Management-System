package in.cdac.hms.service.impl;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.cdac.hms.dto.UserDto;
import in.cdac.hms.exception.ResourceNotFoundException;
import in.cdac.hms.model.User;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.payload.SignUpRequest;
import in.cdac.hms.repository.RoleRepository;
import in.cdac.hms.repository.UserRepository;
import in.cdac.hms.service.IUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;

	@Override
	public ApiResponse saveUser(SignUpRequest signUpRequest) {		
		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setUserName(signUpRequest.getUserName());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_STUDENT")));
		userRepository.save(user);
		return new ApiResponse(true, "User saved successfully");
	}

	@Override
	public UserDto getUser() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));		
		return modelMapper.map(user, UserDto.class);
	}

}
