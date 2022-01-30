package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobTitle;

public interface JobTitleService {
	
	Result add(JobTitle jobTitle);
	Result delete(JobTitle jobTitle);
	Result update(JobTitle jobTitle);
	DataResult<List<JobTitle>> getAll();
	DataResult<JobTitle> getById(int jobTitleId);
	DataResult<JobTitle> getByJobTitle(String jobTitle);
	
}
