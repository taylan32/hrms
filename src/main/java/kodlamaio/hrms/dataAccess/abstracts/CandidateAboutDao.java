package kodlamaio.hrms.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.CandidateAbout;

public interface CandidateAboutDao extends JpaRepository<CandidateAbout, Integer>{

	@Query("FROM CandidateAbout where candidate.id=:candidateId")
	CandidateAbout getOne(int candidateId);
}
