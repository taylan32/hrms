package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.abstracts.JobTitleService;
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
import kodlamaio.hrms.entities.DTOs.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerService employerService;
	private JobTitleService jobTitleService;
	private CityService cityService;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, EmployerService employerService,
			JobTitleService jobTitleService, CityService cityService) {
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerService = employerService;
		this.jobTitleService = jobTitleService;
		this.cityService = cityService;
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		var result = JobAdvertisementValidator.validate(jobAdvertisement);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		jobAdvertisement.setActive(true);
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
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<JobAdvertisement> getById(int jobAdvertisementId) {
		if (!checkIfAdvertisementExists(jobAdvertisementId)) {
			return new ErrorDataResult<JobAdvertisement>(Messages.advertisementNotFound);
		}
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getById(jobAdvertisementId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByEmployerId(int employerId) {
		if (!checkIfEmployerExists(employerId)) {
			return new ErrorDataResult<List<JobAdvertisement>>(Messages.employerNotFound);
		}
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByEmployerId(employerId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByJobTitleId(int jobTitleId) {
		if (!checkIfJobTitleExists(jobTitleId)) {
			return new ErrorDataResult<List<JobAdvertisement>>(Messages.jobTitleNotFound);
		}
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByJobTitleId(jobTitleId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByCityId(int cityId) {
		if (!checkIfCityIdExists(cityId)) {
			return new ErrorDataResult<List<JobAdvertisement>>(Messages.cityNotFound);
		}
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByCityId(cityId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveJobAdvertisement(),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisements() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllPassiveJobAdvertisement(),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsByEmployerId(int employerId) {
		if (!checkIfEmployerExists(employerId)) {
			return new ErrorDataResult<List<JobAdvertisement>>(Messages.employerNotFound);
		}
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllActiveJobAdvertisementByEmployerId(employerId),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisementsByEmployerId(int employerId) {
		if (!checkIfEmployerExists(employerId)) {
			return new ErrorDataResult<List<JobAdvertisement>>(Messages.employerNotFound);
		}
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.jobAdvertisementDao.getAllPassiveJobAdvertisementByEmployerId(employerId),
				Messages.advertisementListed);

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
	

	@Override
	public DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetail() {
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getAllActiveJobAdvertisementWithDetail(),Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getAllPassiveJobAdvertisementWithDetail() {
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getAllPassiveJobAdvertisementWithDetail(),Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getAllJobAdvertisementWithDetail() {
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getAllJobAdvertisementWithDetail(),Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetailByEmployerId(int employerId) {
		if (!checkIfEmployerExists(employerId)) {
			return new ErrorDataResult<List<JobAdvertisementDto>>(Messages.employerNotFound);
		}
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getAllActiveJobAdvertisementWithDetailByEmployerId(employerId),Messages.advertisementListed);
	}
	
	@Override
	public DataResult<List<JobAdvertisementDto>> getAllPassiveJobAdvertisementWithDetailByEmployerId(int employerId) {
		if (!checkIfEmployerExists(employerId)) {
			return new ErrorDataResult<List<JobAdvertisementDto>>(Messages.employerNotFound);
		}
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getAllPassiveJobAdvertisementWithDetailByEmployerId(employerId),Messages.advertisementListed);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetailSortedASC() {
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getAllActiveJobAdvertisementWithDetailSortedASC(),Messages.advertisementListed);
		
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetailSortedDESC() {
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getAllActiveJobAdvertisementWithDetailSortedDESC(),Messages.advertisementListed);
	}
	

	private boolean checkIfAdvertisementExists(int jobAdvertisementId) {
		return this.jobAdvertisementDao.existsById(jobAdvertisementId);
	}

	private boolean checkIfEmployerExists(int employerId) {

		if (this.employerService.getById(employerId).isSuccess()) {
			return true;
		}
		return false;
	}

	private boolean checkIfJobTitleExists(int jobTitleId) {
		if (this.jobTitleService.getById(jobTitleId).isSuccess()) {
			return true;
		}
		return false;
	}

	private boolean checkIfCityIdExists(int cityId) {
		if (this.cityService.getById(cityId).isSuccess()) {
			return true;
		}
		return false;
	}

	

}
