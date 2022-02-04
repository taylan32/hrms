package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkingTypeService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkingTypeDao;
import kodlamaio.hrms.entities.WorkingType;

@Service
public class WorkingTypeManager implements WorkingTypeService {
	
	private WorkingTypeDao workingTypeDao;
	
	@Autowired
	public WorkingTypeManager(WorkingTypeDao workingTypeDao) {
		this.workingTypeDao = workingTypeDao;
	}

	@Override
	public Result add(WorkingType workingType) {
		this.workingTypeDao.save(workingType);
		return new SuccessResult(Messages.workingTypeAdded);
	}

	@Override
	public Result update(WorkingType workingType) {
		this.workingTypeDao.save(workingType);
		return new SuccessResult(Messages.workingTypeUpdated);
	}

	@Override
	public Result delete(WorkingType workingType) {
		this.workingTypeDao.delete(workingType);
		return new SuccessResult(Messages.workingTypedeleted);
	}

	@Override
	public DataResult<List<WorkingType>> getAll() {
		return new SuccessDataResult<List<WorkingType>>(this.workingTypeDao.findAll());
	}

	@Override
	public DataResult<WorkingType> getById(int id) {
		if(!this.workingTypeDao.existsById(id)) {
			return new ErrorDataResult<WorkingType>("Bulunamadi");
		}
		return new SuccessDataResult<WorkingType>(this.workingTypeDao.getById(id));
	}

}
