package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Candidate;

public interface CandidateService {

	Result add(Candidate candidate);
	Result delete(Candidate candidate);
	Result update(Candidate candidate);
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> getById(int candidateId);
	DataResult<Candidate> getByUserId(int userId);
	DataResult<Candidate> getByEmail(String email);
	DataResult<Candidate> getByIdentityNumber(String identityNumber);
}
