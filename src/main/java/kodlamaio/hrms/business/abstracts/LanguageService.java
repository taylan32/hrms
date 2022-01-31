package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Language;

public interface LanguageService {
	
	Result add(Language language);
	Result delete(Language language);
	Result update(Language language);
	DataResult<List<Language>> getByEmployerId(int employerId);

}
