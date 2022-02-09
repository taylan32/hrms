package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.JobExperience;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

		
	@Query("Select e FROM JobExperience e where candidate.id=:candidateId ORDER BY releaseYear DESC ")
	List<JobExperience> getAllByCandidateIdSorted(int candidateId);
	
}
