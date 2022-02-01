package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.SocialMediaAccount;

public interface SocialMediaAccountDao extends JpaRepository<SocialMediaAccount, Integer> {

	@Query("FROM SocialMediaAccount where candidate.id=:candidateId")
	List<SocialMediaAccount> getByCanidateId(int candidateId);
	
	@Query(value= "select * from social_media_accounts where id = accountId",nativeQuery = true)
	SocialMediaAccount getById(int accountId);
	
}
