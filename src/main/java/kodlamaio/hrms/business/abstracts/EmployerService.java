package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Employer;

public interface EmployerService extends BaseEntityService<Employer> {

	DataResult<Employer> getByCompanyName(String companyName);

	DataResult<Employer> getByPhoneNumber(String phoneNumber);

	DataResult<List<Employer>> getAllActiveEmployer();

	DataResult<List<Employer>> getAllPassiveEmployer();

	DataResult<List<Employer>> getByCompanyNameContains(String companyName);
	
	DataResult<Employer> getByEmail(String email);

	Result confirmEmployer(int employerId);
}
