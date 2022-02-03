package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.SocialMediaAccount;

public interface SocialMediaAccountService extends BaseEntityService<SocialMediaAccount>{

	DataResult<List<SocialMediaAccount>> getAllByCandidateId(int candidateId);


}
