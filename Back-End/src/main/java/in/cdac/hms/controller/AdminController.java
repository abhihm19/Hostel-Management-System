package in.cdac.hms.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.hms.dto.AdminDto;
import in.cdac.hms.dto.PaymentDto;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.service.IAdminService;
import in.cdac.hms.service.IPaymentService;
import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
	
	private IAdminService adminService;
	private IPaymentService paymentService;

//	private StudentServiceImpl studentServiceImpl;
//	private RoomServiceImpl roomServiceImpl;	
//	private UserServiceImpl userServiceImpl;
//	private ConcernServiceImpl concernServiceImpl;

//	
//	public AdminController(
//			ConcernServiceImpl concernServiceImpl,			
//			AdminServiceImpl adminServiceImpl,
//			StudentServiceImpl studentServiceImpl,
//			RoomServiceImpl roomServiceImpl,
//			UserServiceImpl userServiceImpl,
//			PaymentServiceImpl paymentServiceImpl
//			) {		
//		this.concernServiceImpl = concernServiceImpl;
//		this.adminServiceImpl = adminServiceImpl;
//		this.studentServiceImpl = studentServiceImpl;
//		this.roomServiceImpl = roomServiceImpl;
//		this.userServiceImpl = userServiceImpl;
//		this.paymentServiceImpl = paymentServiceImpl;
//	}
//	

//	

//	
//	@GetMapping("/update")
//	public ResponseEntity<UserDto> getUser() {
//		return ResponseEntity.ok().body(userServiceImpl.getUser());	
//	}
//	

	
	@GetMapping("/view")
	public ResponseEntity<AdminDto> viewAdmin() {			
    	return ResponseEntity.ok().body(adminService.viewAdmin()); 
    }	

	@PutMapping("/update")
	public ResponseEntity<?> updateAdmin(@RequestBody AdminDto adminDto) {	
		adminService.updateAdmin(adminDto);
    	return ResponseEntity.ok().body(new ApiResponse(true, "Updated Admin successfully")); 
    }
	
	@GetMapping("/payment/get-all-payments")
    public ResponseEntity<Page<PaymentDto>> getTransactions(Pageable pageable) {		
    	return ResponseEntity.ok().body(paymentService.getTransactions(pageable)); 
    }	
}
