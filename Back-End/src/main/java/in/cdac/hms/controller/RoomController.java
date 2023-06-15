package in.cdac.hms.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.hms.dto.RoomDto;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.service.IRoomService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/room")
@RequiredArgsConstructor
public class RoomController {

	private IRoomService roomService;

	@PostMapping("/{hostelId}")
	public ResponseEntity<ApiResponse> addRoom(@RequestBody RoomDto roomDto, @PathVariable int hostelId)
			throws Exception {
		return ResponseEntity.ok().body(roomService.addRoomToHostel(roomDto, hostelId));
	}

	@PutMapping("/{roomId}")
	public ResponseEntity<ApiResponse> updateRoom(@RequestBody RoomDto roomDto, @PathVariable int roomId) {
		return ResponseEntity.ok().body(roomService.updateRoom(roomDto, roomId));
	}

	@GetMapping("/get-all-rooms/{hostelId}")
	public ResponseEntity<Page<RoomDto>> displayRooms(Pageable pageable, @PathVariable int hostelId) {
		return ResponseEntity.ok().body(roomService.displayRooms(pageable, hostelId));
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<ApiResponse> deleteRoom(@PathVariable int roomId) {
		return ResponseEntity.ok().body(roomService.deleteRoom(roomId));
	}
}
