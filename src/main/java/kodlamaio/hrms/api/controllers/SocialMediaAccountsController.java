package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SocialMediaAccountService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.SocialMediaAccount;

@RestController
@RequestMapping("api/socialmediaaccounts")
public class SocialMediaAccountsController {

	private SocialMediaAccountService socialMediaAccountService;

	@Autowired
	public SocialMediaAccountsController(SocialMediaAccountService socialMediaAccountService) {
		this.socialMediaAccountService = socialMediaAccountService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody SocialMediaAccount socialMediaAccount) {

		return ResponseEntity.ok(this.socialMediaAccountService.add(socialMediaAccount));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody SocialMediaAccount socialMediaAccount) {
		return this.socialMediaAccountService.delete(socialMediaAccount);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody SocialMediaAccount socialMediaAccount) {
		return ResponseEntity.ok(this.socialMediaAccountService.update(socialMediaAccount));
	}

	@GetMapping("/getAllByCandidateId")
	public DataResult<List<SocialMediaAccount>> getAllByCandidateId(@RequestParam int candidateId) {
		return this.socialMediaAccountService.getAllByCandidateId(candidateId);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<SocialMediaAccount>> getAll(){
		return this.socialMediaAccountService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<SocialMediaAccount> getById(@RequestParam int socialMediaAccountId) {
		return this.socialMediaAccountService.getById(socialMediaAccountId);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		exceptions.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationErrors.put(fieldName, errorMessage);
		});

		return validationErrors;
	}

}
