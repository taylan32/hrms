package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.School;

public interface SchoolService {
	
	Result add(School school);
	
	Result update(School school);
	
	Result delete(School school);
	
	DataResult<List<School>> getAll();
	
	DataResult<School> getById(int schoolId);
	
	DataResult<List<School>> getByCandidateId(int candidateId);
	
	DataResult<List<School>> getAllSortedByCandidateId(int candidateId);
	
 	

}
