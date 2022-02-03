package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.Skill;

public interface SkillService extends BaseEntityService<Skill> {

	DataResult<List<Skill>> getAllById(int candidateId);

}
