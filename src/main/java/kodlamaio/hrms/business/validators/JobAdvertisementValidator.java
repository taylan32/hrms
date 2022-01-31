package kodlamaio.hrms.business.validators;

import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.JobAdvertisement;

public class JobAdvertisementValidator {

	public static Result validate(JobAdvertisement jobAdvertisement) {

		if (jobAdvertisement.getEmployer() == null) {
			return new ErrorResult(Messages.employerNotFound);
		}
		else if(jobAdvertisement.getCity() == null) {
			return new ErrorResult(Messages.cityNotFound);
		}
		else if(jobAdvertisement.getJobTitle() == null) {
			return new ErrorResult(Messages.jobTitleNotFound);
		}
		return new  SuccessResult();
	}

}
