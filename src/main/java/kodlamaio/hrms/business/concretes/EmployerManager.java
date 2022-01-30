package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.constants.Messages;
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

	public EmployerManager(EmployerDao employerDao) {
		this.employerDao = employerDao;
	}

	@Override
	public Result add(Employer employer) {
		if (checkIfEmployerExists(employer.getId())) {
			return new ErrorResult(Messages.employerExists);
		}
		employer.setConfirmed(false);
		this.employerDao.save(employer);
		return new SuccessResult(Messages.employerAdded);
	}

	@Override
	public Result delete(Employer employer) {
		if (!checkIfEmployerExists(employer.getId())) {
			return new ErrorResult(Messages.employerNotFound);
		}
		this.employerDao.delete(employer);
		return new SuccessResult(Messages.employerDeleted);
	}

	@Override
	public Result update(Employer employer) {
		if(!checkIfEmployerExists(employer.getId())) {
			return new ErrorResult(Messages.candidateNotFound);
		}
		this.employerDao.save(employer);
		return new SuccessResult(Messages.epmloyerUpdated);
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Messages.employerListed);
	}

	@Override
	public DataResult<Employer> getById(int employerId) {
		if (!checkIfEmployerExists(employerId)) {
			return new ErrorDataResult<Employer>(Messages.employerNotFound);
		}
		return new SuccessDataResult<Employer>(this.employerDao.getById(employerId), Messages.employerListed);
	}

	@Override
	public DataResult<Employer> getByCompanyName(String companyName) {
		Employer temp = this.employerDao.getByCompanyName(companyName);
		if (temp == null) {
			return new ErrorDataResult<Employer>(Messages.employerNotFound);
		}
		return new SuccessDataResult<Employer>(this.employerDao.getByCompanyName(companyName), Messages.employerListed);
	}

	@Override
	public DataResult<Employer> getByPhoneNumber(String phoneNumber) {
		Employer temp = this.employerDao.getByPhoneNumber(phoneNumber);
		if (temp == null) {
			return new ErrorDataResult<Employer>(Messages.employerNotFound);
		}
		return new SuccessDataResult<Employer>(this.employerDao.getByPhoneNumber(phoneNumber), Messages.employerListed);
	}

	@Override
	public DataResult<Employer> getByUserId(int userId) {
		Employer temp = this.employerDao.getByUserId(userId);
		if (temp == null) {
			return new ErrorDataResult<Employer>(Messages.employerNotFound);
		}
		return new SuccessDataResult<Employer>(this.employerDao.getByUserId(userId), Messages.employerListed);
	}

	@Override
	public DataResult<List<Employer>> getAllActiveEmployer() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getAllActiveEmployer(), Messages.employerListed);
	}

	@Override
	public DataResult<List<Employer>> getAllInActiveEmployer() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getAllInActiveEmployer(),
				Messages.employerListed);
	}
	
	@Override
	public Result confirmEmployer(int employerId) {
		this.employerDao.confirmEmployer(employerId);
		return new SuccessResult(Messages.epmloyerUpdated);
		
	}

	private boolean checkIfEmployerExists(int employerId) {
		return this.employerDao.existsById(employerId);
	}


}
