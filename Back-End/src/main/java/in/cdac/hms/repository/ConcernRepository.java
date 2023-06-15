package in.cdac.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import in.cdac.hms.model.Concern;

public interface ConcernRepository extends JpaRepository<Concern, Integer> {
	Page<Concern> findAllByCreatedBy(String createdBy, Pageable pageable);
}
