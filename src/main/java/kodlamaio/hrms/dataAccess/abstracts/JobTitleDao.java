package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.JobTitle;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer>{

	JobTitle getByJobTitle(String jobTitle);

	@Query("SELECT t FROM JobTitle t WHERE t.id = id")
	JobTitle getByJobtitleId(int jobTitleId);
}
