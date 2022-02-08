package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobAdvertisement;

public interface JobAdvertisementService extends BaseEntityService<JobAdvertisement> {

	DataResult<List<JobAdvertisement>> getByEmployer_Id(int employerId);

	DataResult<List<JobAdvertisement>> getByCity_Id(int cityId);

	DataResult<List<JobAdvertisement>> getByJobTitle_Id(int jobTitleId);

	DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisement();

	DataResult<List<JobAdvertisement>> getAllWaitingForConfirmation();

	DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementByEmployerId(int employerId);

	DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisementByEmployerId(int employerId);

	DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByCityId(int cityId);

	DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByJobTitleId(int jobTitleId);

	DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedDESC();

	DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedASC();

	Result setIsActiveFalse(int jobAdvertisementId);

	Result setIsActiveTrue(int jobAdvertisementId);

	Result confirmAdvertisement(int jobAdvertisementId);

	DataResult<List<JobAdvertisement>> getAllActiveByPage(int pageNo, int pageSize);
	
	DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedDESCTop6();
	
	DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByEmployerIdSortedDESC(int employerId);

}
