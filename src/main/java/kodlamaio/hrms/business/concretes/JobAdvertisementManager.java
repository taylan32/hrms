package kodlamaio.hrms.business.concretes;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;

import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.business.validators.JobAdvertisementValidator;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {

		var result = JobAdvertisementValidator.validate(jobAdvertisement);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		jobAdvertisement.setActive(true);
		setCreationTime(jobAdvertisement);
		jobAdvertisement.setConfirmed(false);
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult(Messages.advertisementAdded);

	}

	@Override
	public Result delete(JobAdvertisement jobAdvertisement) {
		if (!checkIfAdvertisementExists(jobAdvertisement.getId())) {
			return new ErrorResult(Messages.advertisementNotFound);
		}
		this.jobAdvertisementDao.delete(jobAdvertisement);
		return new SuccessResult(Messages.advertisementDeleted);
	}

	@Override
	public Result update(JobAdvertisement jobAdvertisement) {
		if (!checkIfAdvertisementExists(jobAdvertisement.getId())) {
			return new ErrorResult(Messages.advertisementNotFound);
		}
		var result = JobAdvertisementValidator.validate(jobAdvertisement);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult(Messages.advertisementUpdated);
	}

	@Override
	public Result setIsActiveFalse(int jobAdvertisementId) {
		if (!checkIfAdvertisementExists(jobAdvertisementId)) {
			return new ErrorResult(Messages.advertisementNotFound);
		}
		this.jobAdvertisementDao.setIsActiveFalse(jobAdvertisementId);
		return new SuccessResult(Messages.advertisementUpdated);
	}

	@Override
	public Result setIsActiveTrue(int jobAdvertisementId) {
		if (!checkIfAdvertisementExists(jobAdvertisementId)) {
			return new ErrorResult(Messages.advertisementNotFound);
		}
		this.jobAdvertisementDao.setIsActiveTrue(jobAdvertisementId);
		return new SuccessResult(Messages.advertisementUpdated);

	}

	private boolean checkIfAdvertisementExists(int jobAdvertisementId) {
		return this.jobAdvertisementDao.existsById(jobAdvertisementId);
	}

	private void setCreationTime(JobAdvertisement jobAdvertisement) {
		Date currentDate = new Date(System.currentTimeMillis());
		jobAdvertisement.setCreationDate(currentDate);

	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<JobAdvertisement> getById(int id) {
		if (!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult<JobAdvertisement>(Messages.advertisementNotFound);
		}
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getById(id),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByEmployer_Id(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByEmployer_Id(employerId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByCity_Id(int cityId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByCity_Id(cityId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByJobTitle_Id(int jobTitleId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByJobTitle_Id(jobTitleId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisement() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveJobAdvertisement(),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllWaitingForConfirmation() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllWaitingForConfirmation(),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllActiveJobAdvertisementByEmployerId(employerId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisementByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllPassiveJobAdvertisementByEmployerId(employerId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByCityId(int cityId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllActiveAdvertisementByCityId(cityId), Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByJobTitleId(int jobTitleId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllActiveAdvertisementByJobTitleId(jobTitleId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedDESC() {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllActiveAdvertisementSortedDESC(), Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedASC() {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllActiveAdvertisementSortedASC(), Messages.advertisementListed);
	}

	@Override
	public Result confirmAdvertisement(int jobAdvertisementId) {
		if(!this.jobAdvertisementDao.existsById(jobAdvertisementId)) {
			return new ErrorResult(Messages.advertisementNotFound);
		}
		this.jobAdvertisementDao.confirmAdvertisement(jobAdvertisementId);
		return new SuccessResult();
	}

}
