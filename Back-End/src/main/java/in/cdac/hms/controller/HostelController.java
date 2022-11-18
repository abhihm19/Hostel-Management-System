package in.cdac.hms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.hms.dto.HostelDto;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.service.HostelServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="/admin/hostel")
public class HostelController {
	
	private HostelServiceImpl hostelServiceImpl;
	
	public HostelController(HostelServiceImpl hostelServiceImpl) {
		this.hostelServiceImpl = hostelServiceImpl;
	}
	
	@PostMapping("/")
    public ResponseEntity<?> addHostel(@RequestBody HostelDto hostelDto) {
		hostelServiceImpl.addHostel(hostelDto);
    	return ResponseEntity.ok().body(new ApiResponse(true, "Hostel added successfully")); 
    }
	
	@PutMapping("/")
    public ResponseEntity<?> updateHostel(@RequestBody HostelDto hostelDto) throws Exception {
		hostelServiceImpl.updateHostel(hostelDto);
    	return ResponseEntity.ok().body(new ApiResponse(true, "Hostel updated successfully")); 
    }
	
	@GetMapping("/list")
    public ResponseEntity<List<HostelDto>> displayHostels() {		
    	return ResponseEntity.ok().body(hostelServiceImpl.displayHostels()); 
    }
	
	@GetMapping("/")
    public ResponseEntity<HostelDto> viewHostel(@RequestParam long id) {		
    	return ResponseEntity.ok().body(hostelServiceImpl.viewHostel(id)); 
    }
	
	@DeleteMapping("/")
    public ResponseEntity<?> deleteHostel(@RequestBody HostelDto hostelDto) {
		hostelServiceImpl.deleteHostel(hostelDto);
    	return ResponseEntity.ok().body(new ApiResponse(true, "Hostel deleted successfully")); 
    }

}
