package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobAdvertisementConfirmation;

public interface JobAdvertisementConfirmationService {

	Result add(JobAdvertisementConfirmation jobAdvertisementConfirmation);
	DataResult<List<JobAdvertisementConfirmation>> getAll();
	DataResult<JobAdvertisementConfirmation> getById(int id);
	DataResult<JobAdvertisementConfirmation> getByJobAdvertisement_Id(int jobAdvertisementId);

}
