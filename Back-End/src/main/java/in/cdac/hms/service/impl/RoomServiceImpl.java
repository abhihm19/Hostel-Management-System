package in.cdac.hms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.cdac.hms.dto.RoomDto;
import in.cdac.hms.exception.ResourceNotFoundException;
import in.cdac.hms.model.Hostel;
import in.cdac.hms.model.Room;
import in.cdac.hms.payload.ApiResponse;
import in.cdac.hms.repository.HostelRepository;
import in.cdac.hms.repository.RoomRepository;
import in.cdac.hms.service.IRoomService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

	private HostelRepository hostelRepository;
	private RoomRepository roomRepository;
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addRoomToHostel(RoomDto roomDto, int hostelId) {
		Hostel hostel = hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));
		Room room = new Room();
		room.setHostel(hostel);
		room.setRoomNo(roomDto.getRoomNo());
		roomRepository.save(room);
		return new ApiResponse(true, "Room added successfully");
	}

	@Override
	public Page<RoomDto> displayRooms(Pageable pageable, int hostelId) {
		Hostel hostel = hostelRepository.findById(hostelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));;
		Page<Room> rooms = roomRepository.findAllRoomsByHostel(hostel, pageable);
		List<RoomDto> roomDtos = rooms.stream().map((room) -> modelMapper.map(room, RoomDto.class)).collect(Collectors.toList());
	    return new PageImpl<>(roomDtos, pageable, rooms.getTotalElements());
	}

	@Override
	public ApiResponse updateRoom(RoomDto roomDto, int roomId) {
		Room room = roomRepository.findById(roomDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Room not found"));
		room.setRoomNo(roomDto.getRoomNo());
		roomRepository.save(room);
		return new ApiResponse(true, "Room updated successfully");
	}

	@Override
	public ApiResponse deleteRoom(int roomId) {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found"));
		room.setHostel(null);
		room.setStudent(null);
		roomRepository.delete(room);
		return new ApiResponse(true, "Room deleted successfully");
	}
}
