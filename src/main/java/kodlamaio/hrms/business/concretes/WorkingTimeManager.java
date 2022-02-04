package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkingTimeService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkingTimeDao;
import kodlamaio.hrms.entities.WorkingTime;

@Service
public class WorkingTimeManager implements WorkingTimeService{
	
	private WorkingTimeDao workingTimeDao;
	
	@Autowired
	public WorkingTimeManager(WorkingTimeDao workingTimeDao) {
		this.workingTimeDao = workingTimeDao;
	}

	@Override
	public Result add(WorkingTime workingTime) {
		this.workingTimeDao.save(workingTime);
		return new SuccessResult(Messages.workingTimeAdded);
	}

	@Override
	public Result update(WorkingTime workingTime) {
		this.workingTimeDao.save(workingTime);
		return new SuccessResult(Messages.workingTimeUpdated);
	}

	@Override
	public Result delete(WorkingTime workingTime) {
		this.workingTimeDao.delete(workingTime);
		return new SuccessResult(Messages.workingTimeDeleted);
	}

	@Override
	public DataResult<List<WorkingTime>> getAll() {
		return new SuccessDataResult<List<WorkingTime>>(this.workingTimeDao.findAll());
	}

	@Override
	public DataResult<WorkingTime> getById(int id) {
		if(!this.workingTimeDao.existsById(id)) {
			return new ErrorDataResult<WorkingTime>("Bulunamadi");
		}
		return new SuccessDataResult<WorkingTime>(this.workingTimeDao.getById(id));
	}

}
