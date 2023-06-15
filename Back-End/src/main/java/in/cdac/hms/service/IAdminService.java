package in.cdac.hms.service;

import in.cdac.hms.dto.AdminDto;
import in.cdac.hms.payload.ApiResponse;

public interface IAdminService {	
	AdminDto viewAdmin();
	ApiResponse updateAdmin(AdminDto adminDto);
}
