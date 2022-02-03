package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.Language;

public interface LanguageService extends BaseEntityService<Language> {
	
	DataResult<List<Language>> getByCandidateId(int candidateId);

}
