package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.FavouriteJobAdvertisementService;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.FavouriteJobAdvertisementDao;
import kodlamaio.hrms.entities.FavouriteJobAdvertisement;

@Service
public class FavouriteJobAdvertisementManager implements FavouriteJobAdvertisementService {

	private FavouriteJobAdvertisementDao favouriteJobAdvertisementDao;
	private CandidateService candidateService;
	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public FavouriteJobAdvertisementManager(FavouriteJobAdvertisementDao favouriteJobAdvertisementDao,
			CandidateService candidateService, JobAdvertisementService jobAdvertisementService) {
		this.favouriteJobAdvertisementDao = favouriteJobAdvertisementDao;
		this.candidateService = candidateService;
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@Override
	public Result add(FavouriteJobAdvertisement entity) {
		this.favouriteJobAdvertisementDao.save(entity);
		return new SuccessResult(Messages.advertisementAdded);
	}

	@Override
	public Result update(FavouriteJobAdvertisement entity) {
		this.favouriteJobAdvertisementDao.save(entity);
		return new SuccessResult(Messages.advertisementAdded);
	}

	@Override
	public Result delete(FavouriteJobAdvertisement entity) {
		this.favouriteJobAdvertisementDao.delete(entity);
		return new SuccessResult(Messages.advertisementDeleted);
	}

	@Override
	public DataResult<List<FavouriteJobAdvertisement>> getAll() {
		return new SuccessDataResult<List<FavouriteJobAdvertisement>>(this.favouriteJobAdvertisementDao.findAll(),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<FavouriteJobAdvertisement> getById(int id) {
		if (!this.favouriteJobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult<FavouriteJobAdvertisement>(Messages.advertisementNotFound);
		}
		return new SuccessDataResult<FavouriteJobAdvertisement>(this.favouriteJobAdvertisementDao.getById(id),
				Messages.advertisementListed);
	}

	@Override
	public DataResult<List<FavouriteJobAdvertisement>> getByCandidate_Id(int candidateId) {
		if (this.candidateService.getById(candidateId).getData() == null) {
			return new ErrorDataResult<List<FavouriteJobAdvertisement>>(Messages.candidateNotFound);
		}
		return new SuccessDataResult<List<FavouriteJobAdvertisement>>(
				this.favouriteJobAdvertisementDao.getByCandidate_Id(candidateId), Messages.advertisementListed);
	}

	@Override
	public DataResult<List<FavouriteJobAdvertisement>> getByJobAdvertisement_Id(int jobAdvertismentId) {
		if (this.jobAdvertisementService.getById(jobAdvertismentId).getData() == null) {
			return new ErrorDataResult<List<FavouriteJobAdvertisement>>(Messages.advertisementNotFound);
		}
		return new SuccessDataResult<List<FavouriteJobAdvertisement>>(
				this.favouriteJobAdvertisementDao.getByJobAdvertisement_Id(jobAdvertismentId),
				Messages.advertisementListed);
	}

}
