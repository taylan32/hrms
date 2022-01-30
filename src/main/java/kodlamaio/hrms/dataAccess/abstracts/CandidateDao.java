package kodlamaio.hrms.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

	Candidate getByUserId(int userId);

	Candidate getByIdentityNumber(String identityNumber);


	Candidate getByEmail(String email);

}
