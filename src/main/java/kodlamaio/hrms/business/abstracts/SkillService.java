package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Skill;

public interface SkillService {

	Result add(Skill skill);

	Result update(Skill skill);

	Result delete(Skill skill);

	DataResult<List<Skill>> getAll(int candidateId);

	DataResult<Skill> getById(int skillId);
}
