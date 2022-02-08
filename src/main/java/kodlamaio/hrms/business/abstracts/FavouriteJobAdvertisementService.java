package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.FavouriteJobAdvertisement;

public interface FavouriteJobAdvertisementService extends BaseEntityService<FavouriteJobAdvertisement> {

	DataResult<List<FavouriteJobAdvertisement>> getByCandidate_Id(int candidateId);

	DataResult<List<FavouriteJobAdvertisement>> getByJobAdvertisement_Id(int jobAdvertismentId);

}
