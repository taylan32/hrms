package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import java.util.List;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.CandidateAbout;

public interface CandidateAboutService {

	Result add(CandidateAbout candidateAbout);

	Result delete(CandidateAbout candidateAbout);
	
	Result update(CandidateAbout candidateAbout);
	
	DataResult<List<CandidateAbout>> getOne(int candidateId);
	DataResult<CandidateAbout> getById(int id);
	
}
