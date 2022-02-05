package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.JobAdvertisementConfirmation;

public interface JobAdvertisementConfirmationDao extends JpaRepository<JobAdvertisementConfirmation, Integer> {

	JobAdvertisementConfirmation getByJobAdvertisement_Id(int jobAdvertisementId);
}
