package in.cdac.hms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.cdac.hms.dto.StudentDto;
import in.cdac.hms.payload.AllotmentStatus;
import in.cdac.hms.payload.ApiResponse;

public interface IStudentService {	
	ApiResponse updateStudent(StudentDto studentDto);
	StudentDto viewStudent();
	Page<StudentDto> getAllStudents(Pageable pageable);
	ApiResponse bookRoom(int roomId);
	AllotmentStatus getAllotmentStatus();
	ApiResponse makePayment(String transactionId);
}
