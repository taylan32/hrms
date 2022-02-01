package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.CandidateAbout;

public interface CandidateAboutService {

	Result add(CandidateAbout candidateAbout);

	Result delete(CandidateAbout candidateAbout);
	
	Result update(CandidateAbout candidateAbout);
	
	DataResult<CandidateAbout> getOne(int candidateId);
	
}
