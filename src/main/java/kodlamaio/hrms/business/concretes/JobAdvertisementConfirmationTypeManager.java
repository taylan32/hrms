package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementConfirmationTypeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementConfirmationTypeDao;
import kodlamaio.hrms.entities.JobAdvertisementConfirmationType;

@Service
public class JobAdvertisementConfirmationTypeManager implements JobAdvertisementConfirmationTypeService {

	private JobAdvertisementConfirmationTypeDao jobAdvertisementConfirmationTypeDao;

	@Autowired
	public JobAdvertisementConfirmationTypeManager(
			JobAdvertisementConfirmationTypeDao jobAdvertisementConfirmationTypeDao) {
		this.jobAdvertisementConfirmationTypeDao = jobAdvertisementConfirmationTypeDao;
	}

	@Override
	public Result add(JobAdvertisementConfirmationType entity) {
		this.jobAdvertisementConfirmationTypeDao.save(entity);
		return new SuccessResult("onay tipi eklendi");
	}

	@Override
	public Result update(JobAdvertisementConfirmationType entity) {
		this.jobAdvertisementConfirmationTypeDao.save(entity);
		return new SuccessResult("onay tipi guncellendi");
	}

	@Override
	public Result delete(JobAdvertisementConfirmationType entity) {
		this.jobAdvertisementConfirmationTypeDao.delete(entity);
		return new SuccessResult("onay tipi silindi");
	}

	@Override
	public DataResult<List<JobAdvertisementConfirmationType>> getAll() {
		return new SuccessDataResult<List<JobAdvertisementConfirmationType>>(
				this.jobAdvertisementConfirmationTypeDao.findAll());
	}

	@Override
	public DataResult<JobAdvertisementConfirmationType> getById(int id) {
		if (!this.jobAdvertisementConfirmationTypeDao.existsById(id)) {
			return new ErrorDataResult<JobAdvertisementConfirmationType>();
		}
		return new SuccessDataResult<JobAdvertisementConfirmationType>(
				this.jobAdvertisementConfirmationTypeDao.getById(id));
	}

}
