package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobTitle;

@RestController
@RequestMapping("/api/jobtitles")
public class JobTitlesController {

	private JobTitleService jobTitleService;

	public JobTitlesController(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;

	}

	@GetMapping("/getAll")
	public DataResult<List<JobTitle>> getAll() {
		return this.jobTitleService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<JobTitle> getById(@RequestParam int jobTitleId) {
		return this.jobTitleService.getById(jobTitleId);
	}

	@GetMapping("/getByJobTitle")
	public DataResult<List<JobTitle>> getByJobTitle(@RequestParam String jobTitle) {
		return this.jobTitleService.getByJobTitle(jobTitle);
	}

	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobTitle jobTitle) {
		return ResponseEntity.ok(this.jobTitleService.add(jobTitle));
	}
	

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody JobTitle jobTitle) {
		return ResponseEntity.ok(this.jobTitleService.update(jobTitle));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody JobTitle jobTitle) {
		return this.jobTitleService.delete(jobTitle);
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
