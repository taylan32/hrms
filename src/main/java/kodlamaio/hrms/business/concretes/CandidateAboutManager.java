package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateAboutService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateAboutDao;
import kodlamaio.hrms.entities.CandidateAbout;

@Service
public class CandidateAboutManager implements CandidateAboutService {

	private CandidateAboutDao candidateAboutDao;

	@Autowired
	public CandidateAboutManager(CandidateAboutDao candidateAboutDao) {
		this.candidateAboutDao = candidateAboutDao;
	}

	@Override
	public Result add(CandidateAbout candidateAbout) {
		if (this.candidateAboutDao.existsById(candidateAbout.getId())) {
			this.candidateAboutDao.delete(candidateAbout);
		}
		this.candidateAboutDao.save(candidateAbout);
		return new SuccessResult(Messages.descriptionAdded);
	}

	@Override
	public Result delete(CandidateAbout candidateAbout) {
		this.candidateAboutDao.delete(candidateAbout);
		return new SuccessResult(Messages.descriptionDeleted);
	}

	@Override
	public Result update(CandidateAbout candidateAbout) {
		this.candidateAboutDao.save(candidateAbout);
		return new SuccessResult(Messages.descriptionUpdated);
	}

	@Override
	public DataResult<List<CandidateAbout>> getOne(int candidateId) {
		return new SuccessDataResult<List<CandidateAbout>>(this.candidateAboutDao.getOne(candidateId));
	}

}
