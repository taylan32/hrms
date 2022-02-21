package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerUpdateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerUpdateDao;
import kodlamaio.hrms.entities.EmployerUpdate;

@Service
public class EmployerUpdateManager implements EmployerUpdateService {

	private EmployerUpdateDao employerUpdateDao;
	
	@Autowired
	public EmployerUpdateManager(EmployerUpdateDao employerUpdateDao) {
		this.employerUpdateDao = employerUpdateDao;
	}
	
	@Override
	public Result add(EmployerUpdate entity) {
		this.employerUpdateDao.save(entity);
		return new SuccessResult();
	}

	@Override
	public Result update(EmployerUpdate entity) {
		this.employerUpdateDao.save(entity);
		return new SuccessResult();
	}

	@Override
	public Result delete(EmployerUpdate entity) {
		this.employerUpdateDao.delete(entity);
		return new SuccessResult("Silindi");
	}

	@Override
	public DataResult<List<EmployerUpdate>> getAll() {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.findAll());
	}

	@Override
	public DataResult<EmployerUpdate> getById(int id) {
		if(!this.employerUpdateDao.existsById(id)) {
			return new ErrorDataResult<EmployerUpdate>("Not found");
		}
		return new SuccessDataResult<EmployerUpdate>(this.employerUpdateDao.getById(id));
	}

	@Override
	public DataResult<EmployerUpdate> getByEmployerId(int employerId) {
		return new SuccessDataResult<EmployerUpdate>(this.employerUpdateDao.getByEmployer_Id(employerId));
	}

}
