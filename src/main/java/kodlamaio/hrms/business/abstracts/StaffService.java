package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Staff;

public interface StaffService extends BaseEntityService<Staff> {
	
	Result confirmAdvertisement(int jobAdvertisementId);
	Result confirmEmployer(int employerId);

}
