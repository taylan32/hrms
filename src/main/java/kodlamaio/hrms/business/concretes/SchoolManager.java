package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SchoolDao;
import kodlamaio.hrms.entities.School;

@Service
public class SchoolManager implements SchoolService {

	private SchoolDao schoolDao;
	private CandidateService candidateService;
	
	@Autowired
	public SchoolManager(SchoolDao schoolDao, CandidateService candidateService) {
		this.schoolDao = schoolDao;
		this.candidateService = candidateService;
	}
	
	@Override
	public Result add(School school) {
		setIsCompletedFalse(school);
		this.schoolDao.save(school);
		return new SuccessResult(Messages.schoolAdded);
	}

	@Override
	public Result update(School school) {
		this.schoolDao.save(school);
		return new SuccessResult(Messages.schoolUpdated);
	}

	@Override
	public Result delete(School school) {
		this.schoolDao.delete(school);
		return new SuccessResult(Messages.schoolAdded);
	}

	@Override
	public DataResult<List<School>> getAll() {
		return new SuccessDataResult<List<School>>(this.schoolDao.findAll());
	}

	@Override
	public DataResult<School> getById(int schoolId) {
		return new SuccessDataResult<School>(this.schoolDao.getById(schoolId));
	}

	@Override
	public DataResult<List<School>> getByCandidateId(int candidateId) {
		if(!checkIfCandidateExists(candidateId)) {
			return new ErrorDataResult<List<School>>(Messages.candidateNotFound);
		}
		return new SuccessDataResult<List<School>>(this.schoolDao.getByCandidateId(candidateId));
	}

	@Override
	public DataResult<List<School>> getAllSortedByCandidateId(int candidateId) {
		if(!checkIfCandidateExists(candidateId)) {
			return new ErrorDataResult<List<School>>(Messages.candidateNotFound);
		}
		return new SuccessDataResult<List<School>>(this.schoolDao.getAllSortedByCandidateId(candidateId));
	}

	
	private void setIsCompletedFalse(School school) {
		if(school.getGraduateYear().isBlank()) {
			school.setCompleted(false);
			school.setGraduateYear("Hala devam ediyor");
		}
	}
	
	
	private boolean checkIfCandidateExists(int candidateId) {
		var result = this.candidateService.getById(candidateId);
		if(!result.isSuccess()) {
			return false;
		}
		return true;
	}
	
}
