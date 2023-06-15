package in.cdac.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import in.cdac.hms.model.Hostel;
import in.cdac.hms.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	Page<Room> findAllRoomsByHostel(Hostel hostel, Pageable pageable);
}
