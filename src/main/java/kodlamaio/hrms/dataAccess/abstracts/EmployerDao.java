package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import kodlamaio.hrms.entities.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

	@Query("FROM Employer where id=:id")
	Employer getById(int id);

	Employer getByCompanyName(String companyName);

	@Query("select e from Employer e where e.companyName like %:companyName%")
	List<Employer> getByCompanyNameContains(String companyName);

	@Query("select e from Employer e where e.phoneNumber=:phoneNumber")
	Employer getByPhoneNumber(String phoneNumber);

	@Query("select e from Employer e where e.isConfirmed = true")
	List<Employer> getAllActiveEmployer();

	@Query("select e from Employer e where e.isConfirmed = false")
	List<Employer> getAllPassiveEmployer();

	@Transactional
	@Modifying
	@Query("UPDATE Employer e SET e.isConfirmed = true WHERE id=:employerId")
	void confirmEmployer(int employerId);

}
