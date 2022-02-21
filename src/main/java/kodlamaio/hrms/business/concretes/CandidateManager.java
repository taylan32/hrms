package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.Candidate;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private UserService userService;
	private ImageService imageService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, UserService userService, ImageService imageService) {
		this.candidateDao = candidateDao;
		this.userService = userService;
		this.imageService = imageService;
	}

	@Override
	public Result add(Candidate candidate) {

		var result = checkCandidate(candidate);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}

		this.candidateDao.save(candidate);
		if(this.imageService.getByUserId(candidate.getId()).getData() == null) {
			this.imageService.uploadDefaultImage(candidate);
		}
		return new SuccessResult(Messages.candidateAdded);
	}

	@Override
	public Result update(Candidate candidate) {
		this.candidateDao.save(candidate);
		return new SuccessResult(Messages.candidateUpdated);
	}

	@Override
	public Result delete(Candidate candidate) {
		this.candidateDao.delete(candidate);
		return new SuccessResult(Messages.candidateDeleted);
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), Messages.candidateListed);
	}

	@Override
	public DataResult<Candidate> getById(int id) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getByUserId(id), Messages.candidateListed);
	}

	@Override
	public DataResult<Candidate> getByEmail(String email) {
		if(!this.candidateDao.existsByEmail(email)){
			return new ErrorDataResult<Candidate>(Messages.candidateNotFound);
		}
		return new SuccessDataResult<Candidate>(this.candidateDao.getByEmail(email), Messages.candidateListed);
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getByIdentityNumber(identityNumber),
				Messages.candidateListed);
	}
	

	private boolean checkIfEmailExists(String email) {
		if (this.userService.getByEmail(email).isSuccess()) {
			return false;
		}
		return true;
	}


	private boolean checkIfIdentityNumberExists(String identityNumber) {
		if (getByIdentityNumber(identityNumber).getData() == null) {
			return true;
		}
		return false;
	}

	private Result checkCandidate(Candidate candidate) {
		if (!checkIfEmailExists(candidate.getEmail())) {
			return new ErrorResult(Messages.emailExists);
		}
		if (!checkIfIdentityNumberExists(candidate.getIdentityNumber())) {
			return new ErrorResult(Messages.identityNumberInvalid);
		}
		return new SuccessResult();
	}

	
}
