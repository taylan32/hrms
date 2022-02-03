package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SkillService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SkillDao;
import kodlamaio.hrms.entities.Skill;

@Service
public class SkillManager implements SkillService {

	private SkillDao skillDao;

	@Autowired
	public SkillManager(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@Override
	public Result add(Skill skill) {
		this.skillDao.save(skill);
		return new SuccessResult(Messages.skillAdded);
	}

	@Override
	public Result delete(Skill skill) {
		this.skillDao.delete(skill);
		return new SuccessResult(Messages.skillDeleted);
	}

	@Override
	public Result update(Skill skill) {
		this.skillDao.save(skill);
		return new SuccessResult(Messages.skillUpdated);
	}

	@Override
	public DataResult<List<Skill>> getAllById(int candidateId) {
		return new SuccessDataResult<List<Skill>>(this.skillDao.getByCandidateId(candidateId));
	}

	@Override
	public DataResult<Skill> getById(int skillId) {
		if (this.skillDao.existsById(skillId))
			return new SuccessDataResult<Skill>(this.skillDao.getById(skillId));

		return new ErrorDataResult<Skill>(Messages.skillNotFound);
	}

	@Override
	public DataResult<List<Skill>> getAll() {
		return new SuccessDataResult<List<Skill>>(this.skillDao.findAll());
	}

}
