package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.School;

public interface SchoolDao extends JpaRepository<School, Integer> {

	@Query("FROM School where id=:schoolId")
	School getById(int schoolId);

	@Query("SELECT s FROM School s where candidate_id=:candidateId")
	List<School> getByCandidateId(int candidateId);

	@Query("SELECT s FROM School s where candidate_id=:candidateId ORDER BY graduationYear DESC")
	List<School> getAllSortedByCandidateId(int candidateId);

}
