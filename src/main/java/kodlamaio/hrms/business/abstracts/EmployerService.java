package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Employer;

public interface EmployerService extends BaseEntityService<Employer>{
	/*

	Result add(Employer employer);
	Result delete(Employer employer);
	Result update(Employer employer);
	DataResult<List<Employer>> getAll();
	DataResult<Employer> getById(int employerId);
	*/
//	DataResult<Employer> getByUserId(int userId);
	
	DataResult<Employer> getByCompanyName(String companyName);
	DataResult<Employer> getByPhoneNumber(String phoneNumber);
	DataResult<List<Employer>> getAllActiveEmployer();
	DataResult<List<Employer>> getAllPassiveEmployer();
	DataResult<List<Employer>> getByCompanyNameContains(String companyName);
	
	Result confirmEmployer(int employerId);
}
