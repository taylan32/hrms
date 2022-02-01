package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.SocialMediaAccount;

public interface SocialMediaAccountService {
	
	Result add(SocialMediaAccount socialMediaAccount);

	Result delete(SocialMediaAccount socialMediaAccount);
	
	Result update(SocialMediaAccount socialMediaAccount);
	
	DataResult<List<SocialMediaAccount>> getAll(int candidateId);
	
	DataResult<SocialMediaAccount> getById(int socialMediaAccountId);
	

}
