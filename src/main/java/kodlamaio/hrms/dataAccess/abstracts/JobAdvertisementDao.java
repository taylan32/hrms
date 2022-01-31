package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.JobAdvertisement;
import kodlamaio.hrms.entities.DTOs.JobAdvertisementDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{

	@Query("FROM JobAdvertisement where id=:jobAdvertisementId")
	JobAdvertisement getById(int jobAdvertisementId);
	
	List<JobAdvertisement> getByEmployerId(int employerId);
	
	List<JobAdvertisement> getByCityId(int cityId);
	
	List<JobAdvertisement> getByJobTitleId(int jobTitleId);
	
	@Query("select j from JobAdvertisement j where j.isActive = true")
	List<JobAdvertisement> getAllActiveJobAdvertisement();
	
	
		
	/*
	 * 
	 * 		SELECT customerName, customercity, customermail, salestotal	
			FROM onlinecustomers AS oc
		   INNER JOIN
		   orders AS o
		   ON oc.customerid = o.customerid
		   INNER JOIN
		   sales AS s
		   ON o.orderId = s.orderId
		   
		   "select j.id, j.amount, j.creationDate, j.deadLine, e.companyName jt.jobTitle, c.cityName "
			+ "from Employer e inner join JobAdvertisement j on e.companyName = j.employer.companyName"
			+ "from JobTitle jt inner join JobAdvertisement j on jt.JobTitle = j.jobTitle.jobTitle"
	 * 
	 * 
	 * 
	 * 
	 * + )
	 * */
	
	//select p.productId,p.productName, c.categoryName  from Category c inner join Product p
	  //on c.categoryId = p.categoryId
/*	
	@Query("Select new kodlamaio.hrms.entities.DTOs.JobAdvertisementDto"
			+ "(j.id, j.amount, j.creationDate, j.deadLine, e.companyName, jAdv.jobTitle, jobadv.cityName) "
			+ "From Employer e Inner Join e.jobAdvertisements j"
			+ "From JobTitle jt Inner Join jt.jobAdvertisements jAdv"
			+ "From City c Inner Join c.jobAdvertisements jobadv")
	List<JobAdvertisementDto> getAllActiveJobAdvertisementWithDetail();
	
	*/
	
	/*
	@Query("")
	List<JobAdvertisementDto> getAllActiveJobAdvertisementWithDetailSortedASC();

	
	@Query("")
	List<JobAdvertisementDto> getAllActiveJobAdvertisementWithDetailSortedDESC();
	
	
	@Query("")
	List<JobAdvertisement> getAllActiveJobAdvertisementDetailByEmployerId(int employerId);
	
	*/
	
	
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
