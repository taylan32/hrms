package kodlamaio.hrms.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public Result add(JobTitle jobTitle) {

		if (checkIfJobTitleExists(jobTitle.getId())) { // if job title exists
			return new ErrorResult(Messages.jobTitleExists);
		}
		this.jobTitleDao.save(jobTitle);
		return new SuccessResult(Messages.jobTitleAdded);

	}

	@Override
	public Result delete(JobTitle jobTitle) {

		if (!checkIfJobTitleExists(jobTitle.getId())) {
			return new ErrorResult(Messages.jobTitleNotFound);
		}

		jobTitleDao.deleteById(jobTitle.getId());
		return new SuccessResult(Messages.jodTitleDeleted);
	}

	@Override
	public Result update(JobTitle jobTitle) {

		if (!checkIfJobTitleExists(jobTitle.getId())) {
			return new ErrorResult(Messages.jobTitleNotFound);
		}

		jobTitleDao.save(jobTitle);
		return new SuccessResult(Messages.jobTitleUpdated);
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(jobTitleDao.findAll(), Messages.jobTitleListed);
	}

	@Override
	public DataResult<JobTitle> getById(int jobTitleId) {
		if (checkIfJobTitleExists(jobTitleId)) {
			return new SuccessDataResult<JobTitle>(jobTitleDao.getByJobtitleId(jobTitleId));
		}
		return new ErrorDataResult<JobTitle>(null, Messages.jobTitleNotFound);
	}

	@Override
	public DataResult<JobTitle> getByJobTitle(String jobTitle) {
		return new SuccessDataResult<JobTitle>(jobTitleDao.getByJobTitle(jobTitle),Messages.jobTitleListed);
	}

	
	private boolean checkIfJobTitleExists(int jobTitleId) {
		return jobTitleDao.existsById(jobTitleId);
	}

}
