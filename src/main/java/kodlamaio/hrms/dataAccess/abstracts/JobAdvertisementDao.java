package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.JobAdvertisement;
import kodlamaio.hrms.entities.DTOs.JobAdvertisementDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	@Query("FROM JobAdvertisement where id=:jobAdvertisementId")
	JobAdvertisement getById(int jobAdvertisementId);

	List<JobAdvertisement> getByEmployerId(int employerId);

	List<JobAdvertisement> getByCityId(int cityId);

	List<JobAdvertisement> getByJobTitleId(int jobTitleId);

	@Query("select j from JobAdvertisement j where j.isActive = true")
	List<JobAdvertisement> getAllActiveJobAdvertisement();


	@Query(value = "select j.id, e.company_name,jt.title,ci.city_name,j.amount,j.creation_date,j.dead_line "
			+ "from job_advertisements as j " + "inner join employers as e on j.employer_id = e.id "
			+ "inner join job_titles as jt on j.job_title_id = jt.id "
			+ "inner join cities as ci on j.city_id = ci.id where j.is_active = true", nativeQuery = true)
	List<JobAdvertisementDto> getAllActiveJobAdvertisementWithDetail();

	@Query(value = "select j.id, e.company_name,jt.title,ci.city_name, j.amount,j.creation_date,j.dead_line "
			+ "from job_advertisements as j "
			+ "inner join employers as e on j.employer_id = e.id "
			+ "inner join job_titles as jt on j.job_title_id = jt.id "
			+ "inner join cities as ci on j.city_id = ci.id where j.is_active = false", nativeQuery = true)
	List<JobAdvertisementDto> getAllPassiveJobAdvertisementWithDetail();

	@Query(value="select j.id,e.company_name,jt.title,ci.city_name,j.amount,j.creation_date,j.dead_line,j.is_active "
			+ "from job_advertisements as j "
			+ "inner join employers as e on j.employer_id = e.id "
			+ "inner join job_titles as jt on j.job_title_id = jt.id "
			+ "inner join cities as ci on j.city_id = ci.id", nativeQuery = true)
	List<JobAdvertisementDto> getAllJobAdvertisementWithDetail();
	
	@Query(value="select j.id,e.company_name,jt.title,ci.city_name,j.amount,j.creation_date,j.dead_line,j.is_active "
			+ "from job_advertisements as j "
			+ "inner join employers as e on j.employer_id = e.id "
			+ "inner join job_titles as jt on j.job_title_id = jt.id "
			+ "inner join cities as ci on j.city_id = ci.id "
			+ "where j.employer_id=:employerId and j.is_active = true",nativeQuery = true)
	List<JobAdvertisementDto> getAllActiveJobAdvertisementWithDetailByEmployerId(int employerId);
	
	@Query(value="select j.id,e.company_name,jt.title,ci.city_name,j.amount,j.creation_date,j.dead_line,j.is_active "
			+ "from job_advertisements as j "
			+ "inner join employers as e on j.employer_id = e.id "
			+ "inner join job_titles as jt on j.job_title_id = jt.id "
			+ "inner join cities as ci on j.city_id = ci.id "
			+ "where j.employer_id=:employerId and j.is_active = false",nativeQuery = true)
	List<JobAdvertisementDto> getAllPassiveJobAdvertisementWithDetailByEmployerId(int employerId);

	@Query(value = "select j.id,e.company_name,jt.title,ci.city_name,j.amount,j.creation_date,j.dead_line "
			+ "from job_advertisements as j "
			+ "inner join employers as e on j.employer_id = e.id "
			+ "inner join job_titles as jt on j.job_title_id = jt.id "
			+ "inner join cities as ci on j.city_id = ci.id "
			+ "order by asc",nativeQuery = true)
	List<JobAdvertisementDto> getAllActiveJobAdvertisementWithDetailSortedASC();

	@Query(value = "select j.id,e.company_name,jt.title,ci.city_name,j.amount,j.creation_date,j.dead_line "
			+ "from job_advertisements as j "
			+ "inner join employers as e on j.employer_id = e.id "
			+ "inner join job_titles as jt on j.job_title_id = jt.id "
			+ "inner join cities as ci on j.city_id = ci.id "
			+ "order by desc",nativeQuery = true)
	List<JobAdvertisementDto> getAllActiveJobAdvertisementWithDetailSortedDESC();


	@Query("FROM JobAdvertisement where isActive = false")
	List<JobAdvertisement> getAllPassiveJobAdvertisement();

	@Query("FROM JobAdvertisement where isActive = true and employer_id=:employerId")
	List<JobAdvertisement> getAllActiveJobAdvertisementByEmployerId(int employerId);

	@Query("FROM JobAdvertisement where isActive = false and employer_id=:employerId")
	List<JobAdvertisement> getAllPassiveJobAdvertisementByEmployerId(int employerId);

	@Query("update JobAdvertisement j set j.isActive = false where j.id=:jobAdvertisementId")
	void setIsActiveFalse(int jobAdvertisementId);

	@Query("update JobAdvertisement j set j.isActive = true where j.id=:jobAdvertisementId")
	void setIsActiveTrue(int jobAdvertisementId);

}
