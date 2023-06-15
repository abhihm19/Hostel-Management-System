package in.cdac.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cdac.hms.model.Student;
import in.cdac.hms.model.User;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Optional<Student> findByUser(User user);
}
