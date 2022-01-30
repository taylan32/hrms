package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Employer;

public interface EmployerService {

	Result add(Employer employer);
	Result delete(Employer employer);
	Result update(Employer employer);
	
	DataResult<List<Employer>> getAll();
	DataResult<Employer> getById(int employerId);
	DataResult<Employer> getByCompanyName(String companyName);
	DataResult<Employer> getByPhoneNumber(String phoneNumber);
	DataResult<Employer> getByUserId(int userId);
	DataResult<List<Employer>> getAllActiveEmployer();
	DataResult<List<Employer>> getAllInActiveEmployer();
	
	Result confirmEmployer(int employerId);
}
