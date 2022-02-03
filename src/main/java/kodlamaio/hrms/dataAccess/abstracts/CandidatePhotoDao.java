package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.CandidatePhoto;

public interface CandidatePhotoDao extends JpaRepository<CandidatePhoto, Integer> {

	@Query("FROM CandidatePhoto where candidate.id=:candidateId")
	CandidatePhoto getOneByCandidateId(int candidateId);
	
	@Query(value="select * from candidate_photos where id = candidatePhotoId ",nativeQuery = true)
	CandidatePhoto getById(int candidatePhotoId);
	
}
