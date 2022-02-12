package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SocialMediaAccountService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaAccountDao;
import kodlamaio.hrms.entities.SocialMediaAccount;

@Service
public class SocialMediaAccountManager implements SocialMediaAccountService {

	private SocialMediaAccountDao socialMediaAccountDao;

	@Autowired
	public SocialMediaAccountManager(SocialMediaAccountDao socialMediaAccountDao) {
		this.socialMediaAccountDao = socialMediaAccountDao;
	}

	@Override
	public Result add(SocialMediaAccount socialMediaAccount) {
		String lowerName = socialMediaAccount.getName().toLowerCase();
		socialMediaAccount.setName(lowerName);
		this.socialMediaAccountDao.save(socialMediaAccount);
		return new SuccessResult(Messages.socialMediaAccountAdded);
	}

	@Override
	public Result delete(SocialMediaAccount socialMediaAccount) {
		this.socialMediaAccountDao.delete(socialMediaAccount);
		return new SuccessResult(Messages.socialMediaAccountDeleted);
	}

	@Override
	public Result update(SocialMediaAccount socialMediaAccount) {
		this.socialMediaAccountDao.save(socialMediaAccount);
		return new SuccessResult(Messages.socialMediaAccountUpdated);
	}

	@Override
	public DataResult<List<SocialMediaAccount>> getAllByCandidateId(int candidateId) {
		return new SuccessDataResult<List<SocialMediaAccount>>(this.socialMediaAccountDao.getByCandidateId(candidateId));
	}

	@Override
	public DataResult<List<SocialMediaAccount>> getAll() {
		return new SuccessDataResult<List<SocialMediaAccount>>(this.socialMediaAccountDao.findAll());
	}

	@Override
	public DataResult<SocialMediaAccount> getById(int socialMediaAccountId) {
		if (socialMediaAccountDao.existsById(socialMediaAccountId))
			return new SuccessDataResult<SocialMediaAccount>(this.socialMediaAccountDao.getById(socialMediaAccountId));

		return new ErrorDataResult<SocialMediaAccount>(Messages.socialMediaAccountNotFound);
	}

}
