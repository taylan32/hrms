package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

	Candidate getByUserId(int userId);

	List<Candidate> getByFirstName(String firstName);

	Candidate getByIdentityNumber(String identityNumber);

	List<Candidate> getByLastName(String lastName);

	Candidate getByEmail(String email);

}
