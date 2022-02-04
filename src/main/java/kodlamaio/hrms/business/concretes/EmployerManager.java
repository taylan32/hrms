package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.business.validators.EmployerValidator;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private ImageService imageService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, ImageService imageService) {
		this.employerDao = employerDao;
	}

	@Override
	public Result add(Employer employer) {

		var result = EmployerValidator.validate(employer);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		employer.setConfirmed(false);
		this.employerDao.save(employer);
		if(this.imageService.getByUserId(employer.getId()).getData() == null) {
			this.imageService.uploadDefaultImage(employer);
		}
		return new SuccessResult(Messages.employerAdded);
	}

	@Override
	public Result update(Employer employer) {
		var result = EmployerValidator.validate(employer);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		this.employerDao.save(employer);
		return new SuccessResult(Messages.employerAdded);
	}

	@Override
	public Result delete(Employer employer) {
		this.employerDao.delete(employer);
		return new SuccessResult(Messages.employerDeleted);
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Messages.employerListed);
	}

	@Override
	public DataResult<Employer> getById(int id) {
		if (!this.employerDao.existsById(id)) {
			return new ErrorDataResult<Employer>(Messages.employerNotFound);
		}
		return new SuccessDataResult<Employer>(this.employerDao.getById(id), Messages.employerListed);
	}

	@Override
	public DataResult<Employer> getByCompanyName(String companyName) {
		return new SuccessDataResult<Employer>(this.employerDao.getByCompanyName(companyName), Messages.employerListed);
	}

	@Override
	public DataResult<List<Employer>> getByCompanyNameContains(String companyName) {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getByCompanyNameContains(companyName),
				Messages.employerListed);
	}

	@Override
	public DataResult<Employer> getByPhoneNumber(String phoneNumber) {
		return new SuccessDataResult<Employer>(this.employerDao.getByPhoneNumber(phoneNumber), Messages.employerListed);
	}

	@Override
	public DataResult<List<Employer>> getAllActiveEmployer() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getAllActiveEmployer(), Messages.employerListed);
	}

	@Override
	public DataResult<List<Employer>> getAllPassiveEmployer() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getAllPassiveEmployer(), Messages.employerListed);
	}

	@Override
	public Result confirmEmployer(int employerId) {
		this.employerDao.confirmEmployer(employerId);
		return new SuccessResult(Messages.employerActive);
	}

}
