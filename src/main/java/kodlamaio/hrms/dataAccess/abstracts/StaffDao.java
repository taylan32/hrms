package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.Staff;

public interface StaffDao extends JpaRepository<Staff, Integer> {

	boolean existsByEmailAndPassword(String email, String password);
	
	Staff getByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
	
	Staff getByEmail(String email);
}
