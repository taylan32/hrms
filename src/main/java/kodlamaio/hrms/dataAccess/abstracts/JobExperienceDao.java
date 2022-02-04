package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.JobExperience;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

		
	@Query(value= "select * from job_experiences where candidate_id = candidateId order by release_year desc", nativeQuery = true)
	List<JobExperience> getAllByCandidateIdSorted(int candidateId);
	
}
