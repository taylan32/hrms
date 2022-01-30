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

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Employer;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		this.employerService = employerService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Employer employer) {
		return ResponseEntity.ok(this.employerService.add(employer));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Employer employer) {
		return ResponseEntity.ok(this.employerService.update(employer));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Employer employer) {
		return this.employerService.delete(employer);
	}

	@GetMapping("/getAll")
	public DataResult<List<Employer>> getAll() {
		return this.employerService.getAll();
	}

	@GetMapping("/getByEmployerId")
	public DataResult<Employer> getById(@RequestParam int employerId) {
		return this.employerService.getById(employerId);
	}

	@GetMapping("/getByCompanyName")
	public DataResult<Employer> getByCompanyName(@RequestParam String companyName) {
		return this.employerService.getByCompanyName(companyName);
	}

	@GetMapping("/getByPhoneNumber")
	public DataResult<Employer> getByPhoneNumber(@RequestParam String phoneNumber) {
		return this.employerService.getByPhoneNumber(phoneNumber);
	}

	@GetMapping("/getByUserId")
	public DataResult<Employer> getByUserId(@RequestParam int userId) {
		return this.employerService.getByUserId(userId);
	}

	@GetMapping("/gellAllActive")
	public DataResult<List<Employer>> getAllActiveEmployer() {
		return this.employerService.getAllActiveEmployer();
	}

	@GetMapping("/getAllInactiveEmployer")
	public DataResult<List<Employer>> getAllInActiveEmployer() {
		return this.employerService.getAllInActiveEmployer();
	}

	@GetMapping("/confirmEmployer")
	public Result confirmEmployer(int employerId) {
		return this.employerService.confirmEmployer(employerId);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationExceptions = new HashMap<String, String>();
		exceptions.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationExceptions.put(fieldName, errorMessage);
		});
		return validationExceptions;
	}

}
