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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobExperience;

@RestController
@RequestMapping("/api/jobexperiments")
@CrossOrigin
public class JobExperiencesController {

	private JobExperienceService jobExperimentService;

	@Autowired
	public JobExperiencesController(JobExperienceService jobExperimentService) {
		this.jobExperimentService = jobExperimentService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobExperience jobExperiment) {

		return ResponseEntity.ok(this.jobExperimentService.add(jobExperiment));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody JobExperience jobExperiment) {
		return this.jobExperimentService.delete(jobExperiment);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody JobExperience jobExperiment) {
		return ResponseEntity.ok(this.jobExperimentService.update(jobExperiment));
	}

	@GetMapping("/getAll")
	public DataResult<List<JobExperience>> getAllSorted(@RequestParam int candidateId) {
		return this.jobExperimentService.getAllSorted(candidateId);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validaitonExceptions = new HashMap<String, String>();
		exceptions.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validaitonExceptions.put(fieldName, errorMessage);
		});
		return validaitonExceptions;
	}

}
