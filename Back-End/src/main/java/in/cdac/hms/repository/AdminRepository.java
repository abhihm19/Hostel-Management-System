package in.cdac.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cdac.hms.model.Admin;
import in.cdac.hms.model.User;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByUser(User user);	
}

