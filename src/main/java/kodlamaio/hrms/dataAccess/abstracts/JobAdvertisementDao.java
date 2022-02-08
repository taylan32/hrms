package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import kodlamaio.hrms.entities.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> getByEmployer_Id(int employerId);

	List<JobAdvertisement> getByCity_Id(int cityId);

	List<JobAdvertisement> getByJobTitle_Id(int jobTitleId);

	@Query("select j from JobAdvertisement j where j.isActive = true and j.isConfirmed = true")
	List<JobAdvertisement> getAllActiveJobAdvertisement();

	@Query("FROM JobAdvertisement where isConfirmed = false")
	List<JobAdvertisement> getAllWaitingForConfirmation();

	@Query("FROM JobAdvertisement where isActive = true and employer.id=:employerId and isConfirmed = true")
	List<JobAdvertisement> getAllActiveJobAdvertisementByEmployerId(int employerId);

	@Query("FROM JobAdvertisement where isActive = false and employer.id=:employerId")
	List<JobAdvertisement> getAllPassiveJobAdvertisementByEmployerId(int employerId);

	@Query("FROM JobAdvertisement where isActive = true and city.id=:cityId and isConfirmed = true")
	List<JobAdvertisement> getAllActiveAdvertisementByCityId(int cityId);

	@Query("FROM JobAdvertisement where isActive = true and jobTitle.id=:jobTitleId and isConfirmed = true")
	List<JobAdvertisement> getAllActiveAdvertisementByJobTitleId(int jobTitleId);

	@Query("FROM JobAdvertisement where isActive = true and isConfirmed = true ORDER BY creationDate DESC")
	List<JobAdvertisement> getAllActiveAdvertisementSortedDESC();

	@Query("FROM JobAdvertisement where isActive = true and isConfirmed = true ORDER BY creationDate ASC")
	List<JobAdvertisement> getAllActiveAdvertisementSortedASC();
	
	@Query("FROM JobAdvertisement where isActive = true and isConfirmed = true and employer.id=:employerId ORDER BY creationDate DESC ")
	List<JobAdvertisement> getAllActiveAdvertisementByEmployerIdSortedDESC(int employerId);

	@Query("update JobAdvertisement j set j.isActive = false where j.id=:jobAdvertisementId")
	void setIsActiveFalse(int jobAdvertisementId);

	@Query("update JobAdvertisement j set j.isActive = true where j.id=:jobAdvertisementId")
	void setIsActiveTrue(int jobAdvertisementId);

	// @Query("update JobAdvertisement j set j.isConfirmed = true where
	// j.id=:jobAdvertisementId")
	@Query(value = "update job_advertisements set is_confirmed = true where id=jobAdvertisementId", nativeQuery = true)
	@Transactional
	@Modifying
	void confirmAdvertisement(int jobAdvertisementId);

	List<JobAdvertisement> getAllByIsActiveAndIsConfirmed(boolean isActive, boolean isConfirmed, Pageable pageable);

}
