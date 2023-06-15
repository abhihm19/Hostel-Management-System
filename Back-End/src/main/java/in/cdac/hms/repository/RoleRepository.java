package in.cdac.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cdac.hms.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String roleName);
}
