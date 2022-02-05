package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.JobAdvertisementConfirmationType;

public interface JobAdvertisementConfirmationTypeDao extends JpaRepository<JobAdvertisementConfirmationType, Integer> {

}
