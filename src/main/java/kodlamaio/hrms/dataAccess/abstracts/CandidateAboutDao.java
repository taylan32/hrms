package kodlamaio.hrms.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.CandidateAbout;

public interface CandidateAboutDao extends JpaRepository<CandidateAbout, Integer>{


	@Query("select c FROM CandidateAbout c where c.candidate.id=:candidateId")
	List<CandidateAbout> getByCandidateId(int candidateId);
}
