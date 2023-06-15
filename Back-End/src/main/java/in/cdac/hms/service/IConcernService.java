package in.cdac.hms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.cdac.hms.dto.ConcernDto;
import in.cdac.hms.payload.ApiResponse;

public interface IConcernService {
	ApiResponse addConcern(ConcernDto concernDto);
	ApiResponse updateConcern(ConcernDto concernDto,int concernId);
	ConcernDto viewConcern(int concernId);
	Page<ConcernDto> getAllConcerns(Pageable pageable);
	Page<ConcernDto> getAllConcernsForSingleUser(Pageable pageable);	
}
