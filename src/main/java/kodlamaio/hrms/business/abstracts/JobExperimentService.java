package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobExperiment;

public interface JobExperimentService {

	Result add(JobExperiment jobExperiment);

	Result delete(JobExperiment jobExperiment);

	Result update(JobExperiment jobExperiment);
	
	DataResult<List<JobExperiment>> getAllSorted(int candidateId);
	
}
