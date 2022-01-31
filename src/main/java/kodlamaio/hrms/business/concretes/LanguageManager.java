package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.Language;

@Service
public class LanguageManager implements LanguageService{

	private LanguageDao languageDao;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao) {
		this.languageDao = languageDao;
	}
	
	
	@Override
	public Result add(Language language) {
		this.languageDao.save(language);
		return new SuccessResult(Messages.languageAdded);
	}

	@Override
	public Result delete(Language language) {
		this.languageDao.delete(language);
		return new SuccessResult(Messages.languageDeleted);
	}

	@Override
	public Result update(Language language) {
		this.languageDao.save(language);
		return new SuccessResult(Messages.languageUpdated);
	}

	@Override
	public DataResult<List<Language>> getByEmployerId(int employerId) {
		return new SuccessDataResult<List<Language>>(this.languageDao.getAllByCantidateId(employerId));
	}

}
