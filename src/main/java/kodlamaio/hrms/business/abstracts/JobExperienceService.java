package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobExperience;

public interface JobExperienceService {

	Result add(JobExperience jobExperiment);

	Result delete(JobExperience jobExperiment);

	Result update(JobExperience jobExperiment);

	DataResult<List<JobExperience>> getAllSorted(int candidateId);
	
	DataResult<JobExperience> getById(int id);

}
