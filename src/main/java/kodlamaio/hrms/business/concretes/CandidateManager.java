package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
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

	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		this.candidateDao = candidateDao;
	}

	@Override
	public Result add(Candidate candidate) {
		if(checkIfCandidateExists(candidate.getId())) {
			return new ErrorResult(Messages.candidateExist);
		}
		this.candidateDao.save(candidate);
		return new SuccessResult(Messages.candidateAdded);
	}

	@Override
	public Result delete(Candidate candidate) {
		if(!checkIfCandidateExists(candidate.getId())) {
			return new ErrorResult(Messages.candidateNotFound);
		}
		this.candidateDao.delete(candidate);
		return new SuccessResult(Messages.candidateDeleted);
	}

	@Override
	public Result update(Candidate candidate) {
		if(!checkIfCandidateExists(candidate.getId())) {
			return new ErrorResult(Messages.candidateNotFound);
		}
		this.candidateDao.save(candidate);
		return new SuccessResult(Messages.candidateAdded);
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),Messages.candidateListed);
	}

	@Override
	public DataResult<Candidate> getById(int candidateId) {
		if(checkIfCandidateExists(candidateId)) {
			return new SuccessDataResult<Candidate>(this.candidateDao.getById(candidateId),Messages.candidateListed);
		}
		return new ErrorDataResult<Candidate>(Messages.candidateNotFound);
	}

	@Override
	public DataResult<Candidate> getByUserId(int userId) {
		Candidate temp = this.candidateDao.getByUserId(userId);
		if(temp == null) {
			return new ErrorDataResult<Candidate>(Messages.candidateNotFound);
		}
		return new SuccessDataResult<Candidate>(temp,Messages.candidateListed);
	}


	@Override
	public DataResult<Candidate> getByEmail(String email) {
	
		if(checkIfEmailExists(email)) {
			return new ErrorDataResult<Candidate>(Messages.candidateNotFound);
		}
		return new SuccessDataResult<Candidate>(this.candidateDao.getByEmail(email),Messages.candidateListed);
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		if(checkIfIdentityNumberExists(identityNumber)) {
			return new ErrorDataResult<Candidate>(Messages.identityNumberInvalid);
		}
		return new SuccessDataResult<Candidate>(this.candidateDao.getByIdentityNumber(identityNumber),Messages.candidateListed);
	}
	
	
	
	private boolean checkIfCandidateExists(int candidateId){
		return this.candidateDao.existsById(candidateId);
	}
	
	
	private boolean checkIfEmailExists(String email) {
		var result = this.candidateDao.getByEmail(email);
		if(result == null) {
			return true;
		}
		return false;
	}
	
	private boolean checkIfIdentityNumberExists(String identityNumber) {
		var result = this.candidateDao.getByIdentityNumber(identityNumber);
		if(result == null) {
			return true;
		}
		return false;
	}

}
