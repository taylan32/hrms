package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.Language;

public interface LanguageDao extends JpaRepository<Language, Integer> {

	@Query("From Language where candidate.id=:candidateId")
	List<Language> getAllByCantidateId(int candidateId);
	
	
}
