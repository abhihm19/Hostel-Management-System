package in.cdac.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cdac.hms.model.Payment;
import in.cdac.hms.model.Student;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	Optional<Payment> findByStudent(Student student);
}
