package in.cdac.hms.service;

import in.cdac.hms.dto.UserDto;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.payload.SignUpRequest;

public interface IUserService{	
	ApiResponse saveUser(SignUpRequest signUpRequest);			
	UserDto getUser();
}
