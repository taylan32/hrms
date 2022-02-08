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

import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.School;

@RestController
@RequestMapping("/api/schools")
@CrossOrigin
public class SchoolsController {

	private SchoolService schoolService;

	@Autowired
	public SchoolsController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody School school) {
		return ResponseEntity.ok(this.schoolService.add(school));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody School school) {
		return ResponseEntity.ok(this.schoolService.update(school));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody School school) {
		return this.schoolService.delete(school);
	}

	@GetMapping("/getAll")
	public DataResult<List<School>> getAll() {
		return this.schoolService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<School> getById(@RequestParam int schoolId) {
		return this.schoolService.getById(schoolId);
	}

	@GetMapping("/getByCandidateId")
	public DataResult<List<School>> getByCandidateId(@RequestParam int candidateId) {
		return this.schoolService.getByCandidateId(candidateId);
	}

	@GetMapping("/getByCandidateIdSorted")
	public DataResult<List<School>> getAllSortedByCandidateId(@RequestParam int candidateId) {
		return this.schoolService.getAllSortedByCandidateId(candidateId);
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
