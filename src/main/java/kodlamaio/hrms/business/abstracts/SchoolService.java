package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.School;

public interface SchoolService extends BaseEntityService<School> {

	DataResult<List<School>> getByCandidateId(int candidateId);

	DataResult<List<School>> getAllSortedByCandidateId(int candidateId);

}
