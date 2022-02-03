package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.Candidate;

public interface CandidateService extends BaseEntityService<Candidate> {

	DataResult<Candidate> getByEmail(String email);

	DataResult<Candidate> getByIdentityNumber(String identityNumber);

}
