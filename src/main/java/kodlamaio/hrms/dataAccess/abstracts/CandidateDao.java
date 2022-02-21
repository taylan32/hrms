package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

	@Query("FROM Candidate where id=:userId")
	Candidate getByUserId(int userId);

	Candidate getByIdentityNumber(String identityNumber);

	Candidate getByEmail(String email);
	
	boolean existsByEmail(String email);
	
	boolean existsByEmailAndPassword(String email, String password);
	
	Candidate getByEmailAndPassword(String email, String password);

}
