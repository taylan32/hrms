package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.Skill;

public interface SkillDao extends JpaRepository<Skill, Integer> {

	@Query("FROM Skill where candidate.id=:candidateId")
	List<Skill> getByCandidateId(int candidateId);

	@Query("FROM Skill where id=:skillId")
	Skill getById(int skillId);

}
