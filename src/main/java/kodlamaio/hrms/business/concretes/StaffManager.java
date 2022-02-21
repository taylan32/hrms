package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.abstracts.StaffService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.StaffDao;
import kodlamaio.hrms.entities.Staff;

@Service
public class StaffManager implements StaffService {
	
	private StaffDao staffDao;
	private JobAdvertisementService jobAdvertisementService;
	private EmployerService employerService;
	
	@Autowired
	public StaffManager(StaffDao staffDao, 
						JobAdvertisementService jobAdvertisementService, 
						EmployerService employerService) {
		this.staffDao = staffDao;
		this.jobAdvertisementService = jobAdvertisementService;
		this.employerService = employerService;
	}

	@Override
	public Result add(Staff staff) {
		this.staffDao.save(staff);
		return new SuccessResult(Messages.staffAdded);
	}

	@Override
	public Result update(Staff staff) {
		this.staffDao.save(staff);
		return new SuccessResult(Messages.staffUpdated);
	}

	@Override
	public Result delete(Staff staff) {
		this.staffDao.delete(staff);
		return new SuccessResult(Messages.staffDeleted);
	}

	@Override
	public DataResult<List<Staff>> getAll() {
		return new SuccessDataResult<List<Staff>>(this.staffDao.findAll());
	}

	@Override
	public DataResult<Staff> getById(int id) {
		if(!this.staffDao.existsById(id)) {
			return new ErrorDataResult<Staff>();
		}
		
		return new SuccessDataResult<Staff>(this.staffDao.getById(id));
	}
	
	@Override
	public DataResult<Staff> getByEmail(String email) {
		if(!this.staffDao.existsByEmail(email)) {
			return new ErrorDataResult<>("Not found");
		}
		return new SuccessDataResult<Staff>(this.staffDao.getByEmail(email));
	}

	@Override
	public Result confirmAdvertisement(int jobAdvertisementId) {
		this.jobAdvertisementService.confirmAdvertisement(jobAdvertisementId);
		return new SuccessResult();
	}

	@Override
	public Result confirmEmployer(int employerId) {
		this.employerService.confirmEmployer(employerId);
		return new SuccessResult();
	}

}
