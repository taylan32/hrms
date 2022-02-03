package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.JobTitle;

public interface JobTitleService extends BaseEntityService<JobTitle> {

	DataResult<JobTitle> getByJobTitle(String jobTitle);

}
