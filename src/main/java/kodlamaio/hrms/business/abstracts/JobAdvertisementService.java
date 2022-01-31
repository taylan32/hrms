package kodlamaio.hrms.business.abstracts;

import java.util.List;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobAdvertisement;

public interface JobAdvertisementService {

	Result add(JobAdvertisement jobAdvertisement);

	Result delete(JobAdvertisement jobAdvertisement);

	Result update(JobAdvertisement jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> getAll();
	
	DataResult<JobAdvertisement> getById(int jobAdvertisementId);
	
	DataResult<List<JobAdvertisement>> getByEmployerId(int employerId);
	
	DataResult<List<JobAdvertisement>> getByJobTitleId(int jobTitleId);
	
	DataResult<List<JobAdvertisement>> getByCityId(int cityId);
	
	DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements();
	
	DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisements();
	
	DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsByEmployerId(int employerId);
	
	DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisementsByEmployerId(int employerId);
	
	Result setIsActiveFalse(int jobAdvertisementId);
	
	Result setIsActiveTrue(int jobAdvertisementId);
	
	
	
	
	
}
