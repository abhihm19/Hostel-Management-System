package in.cdac.hms.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import in.cdac.hms.dto.AdminDto;
import in.cdac.hms.exception.ResourceNotFoundException;
import in.cdac.hms.model.Admin;
import in.cdac.hms.model.User;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.repository.AdminRepository;
import in.cdac.hms.repository.UserRepository;
import in.cdac.hms.service.IAdminService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {	
	
	private AdminRepository adminRepository;
	private ModelMapper modelMapper;
	private UserRepository userRepository;

	@Override
	public AdminDto viewAdmin() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Admin admin = adminRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found"));	
		return modelMapper.map(admin, AdminDto.class);
	}	

	@Override
	public ApiResponse updateAdmin(AdminDto adminDto) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Admin admin = adminRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
		admin.setDepartment(adminDto.getDepartment());
		admin.setDesignation(adminDto.getDesignation());
		admin.setEmailId(adminDto.getEmailId());
		admin.setMobileNo(adminDto.getMobileNo());
		adminRepository.save(admin);
		return new ApiResponse(true, "Admin details updated successfully");
	}
}
