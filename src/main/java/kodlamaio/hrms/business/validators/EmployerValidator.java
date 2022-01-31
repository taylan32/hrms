package kodlamaio.hrms.business.validators;

import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.Employer;

public class EmployerValidator {

	public static Result validate(Employer employer) {
		
		if(!checkIfEmailValid(employer.getEmail(), employer.getWebSite())) {
			return new ErrorResult(Messages.emailInvalid);
		}
				
		return new SuccessResult();
	}
	
	
	// web adresi ile aynı domaine sahip eposta olmalı
	private static boolean checkIfEmailValid(String email, String webSite){
		if(email.contains(webSite+"@")) {
			return true;
		}
		return false;
	}
	
}
