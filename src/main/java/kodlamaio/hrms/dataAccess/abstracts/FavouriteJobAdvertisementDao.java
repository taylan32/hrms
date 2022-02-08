package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.FavouriteJobAdvertisement;

public interface FavouriteJobAdvertisementDao extends JpaRepository<FavouriteJobAdvertisement, Integer> {

	List<FavouriteJobAdvertisement> getByCandidate_Id(int candidateId);

	List<FavouriteJobAdvertisement> getByJobAdvertisement_Id(int jobAdvertismentId);

}
