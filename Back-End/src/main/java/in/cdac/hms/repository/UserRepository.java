package in.cdac.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cdac.hms.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
	Boolean existsByUserName(String userName);
}
