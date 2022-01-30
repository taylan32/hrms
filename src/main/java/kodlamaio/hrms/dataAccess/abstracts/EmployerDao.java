package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

	Employer getByUserId(int userId);

	Employer getByCompanyName(String companyName);
	
	@Query("select e from Employer e where e.id = id")
	Employer getById(int id);
	
	@Query("select e from Employer e where e.companyName like %:companyName%")
	List<Employer> getByCompanyNameContains(String companyName);

	@Query("select e from Employer e where e.phoneNumber=:phoneNumber")
	Employer getByPhoneNumber(String phoneNumber);

	@Query("select e from Employer e where e.isConfirmed = true")
	List<Employer> getAllActiveEmployer();

	@Query("select e from Employer e where e.isConfirmed = false")
	List<Employer> getAllInActiveEmployer();
	
	@Query("update Employer e set e.isConfirmed=tue where e.id=:employerId")
	void confirmEmployer(int employerId);
	

}
