package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.JobTitle;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer>{

	JobTitle getByJobTitle(String jobTitle);
}
