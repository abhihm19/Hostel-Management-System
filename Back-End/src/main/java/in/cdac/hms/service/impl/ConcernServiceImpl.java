package in.cdac.hms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import in.cdac.hms.dto.ConcernDto;
import in.cdac.hms.exception.ResourceNotFoundException;
import in.cdac.hms.model.Concern;
import in.cdac.hms.model.Student;
import in.cdac.hms.model.User;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.repository.ConcernRepository;
import in.cdac.hms.repository.StudentRepository;
import in.cdac.hms.repository.UserRepository;
import in.cdac.hms.service.IConcernService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcernServiceImpl implements IConcernService {	
	
	private ConcernRepository concernRepository;	
	private StudentRepository studentRepository;
	private UserRepository userRepository;	
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addConcern(ConcernDto concernDto) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		Concern concern = new Concern();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		concern.setSubject(concernDto.getSubject());
		concern.setMessage(concernDto.getMessage());
		concern.setStudent(student);
		concernRepository.save(concern);
		return new ApiResponse(true, "Concern raised successfully");
	}
	
	@Override
	public ApiResponse updateConcern(ConcernDto concernDto, int concernId) {
		Concern concern = concernRepository.findById(concernId)
				.orElseThrow(() -> new ResourceNotFoundException("Concern not found"));
		concern.setMessage(concernDto.getMessage());
		concernRepository.save(concern);
		return new ApiResponse(true, "Concern updated successfully");
	}

	@Override
	public ConcernDto viewConcern(int concernId) {
		Concern concern = concernRepository.findById(concernId)
				.orElseThrow(() -> new ResourceNotFoundException("Concern not found"));
		return modelMapper.map(concern, ConcernDto.class);
	}
	
	@Override
	public Page<ConcernDto> getAllConcernsForSingleUser(Pageable pageable) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		Page<Concern> concerns =  concernRepository.findAllByCreatedBy(userName, pageable);
		List<ConcernDto> concernDtos = concerns.stream().map((concern) -> mapToConcernDto(concern)).collect(Collectors.toList());
	    return new PageImpl<>(concernDtos, pageable, concerns.getTotalElements());
	}
	
	
	@Override
	public Page<ConcernDto> getAllConcerns(Pageable pageable) {
		Page<Concern> concerns =  concernRepository.findAll(pageable);
		List<ConcernDto> concernDtos = concerns.stream().map((concern) -> mapToConcernDto(concern)).collect(Collectors.toList());
	    return new PageImpl<>(concernDtos, pageable, concerns.getTotalElements());
	}

	private ConcernDto mapToConcernDto(Concern concern) {
		ConcernDto concernDto = new ConcernDto();
		concernDto.setId(concern.getId());
		concernDto.setMessage(concern.getMessage());
		concernDto.setSubject(concern.getSubject());		
		concernDto.setStudentName(concern.getStudent().getUser().getFirstName());
		try {
			concernDto.setHostelName(concern.getStudent().getRoom().getHostel().getName());
			concernDto.setRoomNo(concern.getStudent().getRoom().getRoomNo());
		} catch (NullPointerException e) {
			concernDto.setHostelName("Not Booked");
			concernDto.setRoomNo(-1);
		}
		return concernDto;
	}
}
