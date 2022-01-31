package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.JobExperiment;

public interface JobExperimentDao extends JpaRepository<JobExperiment, Integer> {

		
	@Query(value= "select * from job_experiments where candidate_id = candidateId order by release_year desc", nativeQuery = true)
	List<JobExperiment> getAllByCandidateIdSorted(int candidateId);
	
}
