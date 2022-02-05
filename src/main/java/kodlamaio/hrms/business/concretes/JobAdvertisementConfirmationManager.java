package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementConfirmationService;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementConfirmationDao;
import kodlamaio.hrms.entities.JobAdvertisementConfirmation;

@Service
public class JobAdvertisementConfirmationManager implements JobAdvertisementConfirmationService {

	private JobAdvertisementConfirmationDao jobAdvertisementConfirmationDao;
	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementConfirmationManager(JobAdvertisementConfirmationDao jobAdvertisementConfirmationDao,
			JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementConfirmationDao = jobAdvertisementConfirmationDao;
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@Override
	public Result add(JobAdvertisementConfirmation jobAdvertisementConfirmation) {
		jobAdvertisementConfirmation.setConfirmationDate(LocalDateTime.now());
		var result = this.jobAdvertisementService
				.confirmAdvertisement(jobAdvertisementConfirmation.getJobAdvertisement().getId());
		if (!result.isSuccess()) {
			return new ErrorResult(Messages.advertisementCannotBeConfirmed);
		}
		this.jobAdvertisementConfirmationDao.save(jobAdvertisementConfirmation);
		return new SuccessResult(Messages.advertisementConfirmed);
	}

	@Override
	public DataResult<List<JobAdvertisementConfirmation>> getAll() {
		return new SuccessDataResult<List<JobAdvertisementConfirmation>>(
				this.jobAdvertisementConfirmationDao.findAll());
	}

	@Override
	public DataResult<JobAdvertisementConfirmation> getById(int id) {
		if (!this.jobAdvertisementConfirmationDao.existsById(id)) {
			return new ErrorDataResult<JobAdvertisementConfirmation>();
		}
		return new SuccessDataResult<JobAdvertisementConfirmation>(this.jobAdvertisementConfirmationDao.getById(id));
	}

	@Override
	public DataResult<JobAdvertisementConfirmation> getByJobAdvertisement_Id(int jobAdvertisementId) {
		return new SuccessDataResult<JobAdvertisementConfirmation>(
				this.jobAdvertisementConfirmationDao.getByJobAdvertisement_Id(jobAdvertisementId));
	}

}
