package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.entities.JobExperience;

@Service
public class JobExperienceManager implements JobExperienceService {

	private JobExperienceDao jobExperimentDao;

	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperimentDao) {
		this.jobExperimentDao = jobExperimentDao;
	}

	@Override
	public Result add(JobExperience jobExperiment) {
		if (checkIfContinue(jobExperiment)) {
			jobExperiment.setContinued(true);
			jobExperiment.setReleaseYear("Hala devam ediyor");
		}
		this.jobExperimentDao.save(jobExperiment);
		return new SuccessResult(Messages.jobExperimentAdded);
	}

	@Override
	public Result delete(JobExperience jobExperiment) {
		this.jobExperimentDao.delete(jobExperiment);
		return new SuccessResult(Messages.jobExperimentDeleted);
	}

	@Override
	public Result update(JobExperience jobExperiment) {
		this.jobExperimentDao.save(jobExperiment);
		return new SuccessResult(Messages.jobExperimentUpdated);
	}
	
	@Override
	public DataResult<JobExperience> getById(int id) {
		if(!this.jobExperimentDao.existsById(id)) {
			return new ErrorDataResult<JobExperience>("Not found");
		}
		return new SuccessDataResult<JobExperience>(this.jobExperimentDao.getById(id));
	}

	@Override
	public DataResult<List<JobExperience>> getAllSorted(int candidateId) {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperimentDao.getAllByCandidateIdSorted(candidateId));
	}

	private boolean checkIfContinue(JobExperience experiment) {
		if (experiment.getReleaseYear().isBlank()) {
			return true;
		}
		return false;
	}

}
