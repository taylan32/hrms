package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.JobTitle;
import java.util.List;

public interface JobTitleService extends BaseEntityService<JobTitle> {

	DataResult<List<JobTitle>> getByJobTitle(String jobTitle);

}
