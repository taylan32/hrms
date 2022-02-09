package kodlamaio.hrms.business.concretes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		if (!this.jobAdvertisementDao.existsById(jobAdvertisementId)) {
			return new ErrorResult(Messages.advertisementNotFound);
		}
		this.jobAdvertisementDao.confirmAdvertisement(jobAdvertisementId);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveByPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllByIsActiveAndIsConfirmed(true, true, pageable));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedDESCTop6() {

		return new SuccessDataResult<List<JobAdvertisement>>(getAllActiveByPage(1, 6).getData());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByEmployerIdSortedDESC(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllActiveAdvertisementByEmployerIdSortedDESC(employerId));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(
			int cityId, int jobTitleId, int workingTimeId, int workingTypeId) {

		List<JobAdvertisement> result = new ArrayList<JobAdvertisement>();
		Stream<JobAdvertisement> stream = getAllActiveAdvertisementSortedDESC().getData().stream();

		Predicate<JobAdvertisement> cityCondition = cityId != 0
				? (jobAdvertisement -> jobAdvertisement.getCity().getId() == cityId)
				: (jobAdvertisement -> jobAdvertisement.getCity().getId() > 0);
		Predicate<JobAdvertisement> jobTitleCondition = jobTitleId != 0
				? (jobAdvertisement -> jobAdvertisement.getJobTitle().getId() == jobTitleId)
				: (jobAdvertisement -> jobAdvertisement.getJobTitle().getId() > 0);
		Predicate<JobAdvertisement> workingTimeCondition = workingTimeId != 0
				? (jobAdvertisement -> jobAdvertisement.getWorkingTime().getId() == workingTimeId)
				: (jobAdvertisement -> jobAdvertisement.getWorkingTime().getId() > 0);
		Predicate<JobAdvertisement> workingTypeCondition = workingTypeId != 0
				? (jobAdvertisement -> jobAdvertisement.getWorkingType().getId() == workingTypeId)
				: (jobAdvertisement -> jobAdvertisement.getWorkingType().getId() > 0);

		stream.filter(workingTypeCondition).filter(workingTimeCondition).filter(jobTitleCondition).filter(cityCondition)
				.forEach(jobAdvertisement -> result.add(jobAdvertisement));

		return new SuccessDataResult<List<JobAdvertisement>>(result);

	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementPageableFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(
			int cityId, int jobTitleId, int workingTimeId, int workingTypeId, int pageNo, int pageSize) {

		int skipCount = (pageNo - 1) * pageSize;

		List<JobAdvertisement> result = getAllActiveAdvertisementFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(
				cityId, jobTitleId, workingTimeId, workingTypeId).getData();

		return new SuccessDataResult<List<JobAdvertisement>>(
				result.stream().skip(skipCount).collect(Collectors.toList()));
		
		

	}

}
