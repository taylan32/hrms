package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobExperimentService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobExperimentDao;
import kodlamaio.hrms.entities.JobExperiment;

@Service
public class JobExperimentManager implements JobExperimentService {

	private JobExperimentDao jobExperimentDao;

	@Autowired
	public JobExperimentManager(JobExperimentDao jobExperimentDao) {
		this.jobExperimentDao = jobExperimentDao;
	}

	@Override
	public Result add(JobExperiment jobExperiment) {
		if (checkIfContinue(jobExperiment)) {
			jobExperiment.setContinued(true);
			jobExperiment.setReleaseYear("Hala devam ediyor");
		}
		this.jobExperimentDao.save(jobExperiment);
		return new SuccessResult(Messages.jobExperimentAdded);
	}

	@Override
	public Result delete(JobExperiment jobExperiment) {
		this.jobExperimentDao.delete(jobExperiment);
		return new SuccessResult(Messages.jobExperimentDeleted);
	}

	@Override
	public Result update(JobExperiment jobExperiment) {
		this.jobExperimentDao.save(jobExperiment);
		return new SuccessResult(Messages.jobExperimentUpdated);
	}

	@Override
	public DataResult<List<JobExperiment>> getAllSorted(int candidateId) {
		return new SuccessDataResult<List<JobExperiment>>(this.jobExperimentDao.findAll());
	}

	private boolean checkIfContinue(JobExperiment experiment) {
		if (experiment.getReleaseYear().isBlank()) {
			return true;
		}
		return false;
	}

}
