package in.cdac.hms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.cdac.hms.dto.HostelDto;
import in.cdac.hms.payload.ApiResponse;

public interface IHostelService {	
	ApiResponse addHostel(HostelDto hostelDto);
	ApiResponse updateHostel(HostelDto hostelDto, int hostelId);
	HostelDto viewHostel(int hostelId);
	Page<HostelDto> getAllHostels(Pageable pageable);
}
