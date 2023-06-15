package in.cdac.hms.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import in.cdac.hms.dto.StudentDto;
import in.cdac.hms.exception.ResourceNotFoundException;
import in.cdac.hms.model.Payment;
import in.cdac.hms.model.Room;
import in.cdac.hms.model.Student;
import in.cdac.hms.model.User;
import in.cdac.hms.payload.AllotmentStatus;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.repository.PaymentRepository;
import in.cdac.hms.repository.RoomRepository;
import in.cdac.hms.repository.StudentRepository;
import in.cdac.hms.repository.UserRepository;
import in.cdac.hms.service.IStudentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

	private StudentRepository studentRepository;
	private UserRepository userRepository;
	private RoomRepository roomRepository;
	private PaymentRepository paymentRepository;
	private ModelMapper modelMapper;

	@Override
	public Page<StudentDto> getAllStudents(Pageable pageable) {
		Page<Student> students = studentRepository.findAll(pageable);
		List<StudentDto> studentDtos = students.stream().map((student) -> mapToStudentDto(student)).collect(Collectors.toList());
	    return new PageImpl<>(studentDtos, pageable, students.getTotalElements());
	}

	@Override
	public ApiResponse updateStudent(StudentDto studentDto) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		modelMapper.map(studentDto, Student.class);
		studentRepository.save(student);
		return new ApiResponse(true, "Student registered successfully");
	}

	@Override
	public StudentDto viewStudent() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName(user.getFirstName());
		studentDto.setLastName(user.getLastName());
		studentDto.setUserName(user.getUserName());
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		studentDto.setEmailId(student.getEmailId());
		studentDto.setMobileNo(student.getMobileNo());
		studentDto.setCourse(student.getCourse());
		studentDto.setDateOfBirth(student.getDateOfBirth());
		studentDto.setGender(student.getGender());
		studentDto.setAddress(student.getAddress());
		return studentDto;
	}

	private StudentDto mapToStudentDto(Student student) {
		StudentDto studentDto = new StudentDto();
		User user = student.getUser();
		studentDto.setFirstName(user.getFirstName());
		studentDto.setLastName(user.getLastName());
		studentDto.setUserName(user.getUserName());
		studentDto.setEmailId(student.getEmailId());
		studentDto.setMobileNo(student.getMobileNo());
		studentDto.setCourse(student.getCourse());
		studentDto.setDateOfBirth(student.getDateOfBirth());
		studentDto.setGender(student.getGender());
		studentDto.setAddress(student.getAddress());
		try {
			studentDto.setRoomNo(student.getRoom().getRoomNo());
			studentDto.setHostelName(student.getRoom().getHostel().getName());
		} catch (NullPointerException e) {
			studentDto.setRoomNo(-1);
			studentDto.setHostelName("Not Booked");
		}
		return studentDto;
	}
	
	@Override
	public ApiResponse makePayment(String transactionId) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		Payment payment = new Payment();
		payment.setTransactionId(transactionId);
		payment.setStudent(student);
		payment.setTransactionDate(LocalDateTime.now());
		if (transactionId != null) {
			payment.setTransactionStatus(true);
			paymentRepository.save(payment);		
			return new ApiResponse(true, "Payment was successfully completed");		
		} else {
			payment.setTransactionStatus(false);
			paymentRepository.save(payment);		
			return new ApiResponse(false, "Payment failed. Please try again");		
		}		
	}
	

	@Override
	public ApiResponse bookRoom(int roomId) {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		Payment payment = paymentRepository.findByStudent(student)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
		if (payment.getTransactionStatus() && student.getRoom() == null) {
			Room room = roomRepository.findById(roomId)
					.orElseThrow(() -> new ResourceNotFoundException("Room not found"));
			room.setIsVacant(false);
			student.setRoom(room);
			studentRepository.save(student);			
			roomRepository.save(room);
			return new ApiResponse(true, "Room booked successfully");
		} 
		return new ApiResponse(false, "Failed to book  room. Please try again");
	}
	
	@Override
	public AllotmentStatus getAllotmentStatus() {
		String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));;
		Student student = studentRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		Payment payment = paymentRepository.findByStudent(student)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
		AllotmentStatus allotmentStatus = new AllotmentStatus();
		if (payment != null) {
			allotmentStatus.setRoomNo(student.getRoom().getRoomNo());
			allotmentStatus.setHostelName(student.getRoom().getHostel().getName());
			allotmentStatus.setContactPerson(student.getRoom().getHostel().getContactPerson());
			allotmentStatus.setContactPhone(student.getRoom().getHostel().getContactMobileNo());
			allotmentStatus.setHostelAddress(student.getRoom().getHostel().getAddress());
			allotmentStatus.setAllotmentStatus(true);
			allotmentStatus.setPaymentStatus(payment.getTransactionStatus());
			return allotmentStatus;
		}else {
			allotmentStatus.setAllotmentStatus(false);
			allotmentStatus.setPaymentStatus(false);
			return allotmentStatus;
		}
	}
}
