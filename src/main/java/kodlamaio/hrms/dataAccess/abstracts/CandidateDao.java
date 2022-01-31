package kodlamaio.hrms.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

	Candidate getByUserId(int userId);

	Candidate getByIdentityNumber(String identityNumber);
	
	@Query("FROM Candidate where id=:candidateId")
	Candidate getById(int candidateId);

	Candidate getByEmail(String email);

}
