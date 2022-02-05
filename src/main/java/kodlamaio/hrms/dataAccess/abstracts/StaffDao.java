package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.Staff;

public interface StaffDao extends JpaRepository<Staff, Integer> {

}
