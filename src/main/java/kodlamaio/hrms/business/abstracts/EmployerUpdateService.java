package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.EmployerUpdate;

public interface EmployerUpdateService extends BaseEntityService<EmployerUpdate>{
	
	DataResult<EmployerUpdate> getByEmployerId(int employerId);

}
