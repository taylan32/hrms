package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.School;

public interface SchoolDao extends JpaRepository<School, Integer> {
	
	
	@Query("FROM School where id=:schoolId")
	School getById(int schoolId);
	
	
	@Query(value="Select * from School  where candidateId = candidateId",nativeQuery = true)
	List<School> getByCandidateId(int candidateId);
	
	
	@Query(value="select * from schools where candidate_id = candidateId order by graduation_date desc",nativeQuery = true)
	List<School> getAllSortedByCandidateId(int candidateId);	
	
	
	
	

}
