package in.cdac.hms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.cdac.hms.dto.RoomDto;
import in.cdac.hms.payload.ApiResponse;

public interface IRoomService {	
	ApiResponse addRoomToHostel(RoomDto roomDto, int hostelId) throws Exception;
	Page<RoomDto> displayRooms(Pageable pageable, int hostelId);
	ApiResponse updateRoom(RoomDto roomDto, int roomId);
	ApiResponse deleteRoom(int roomId);
}
