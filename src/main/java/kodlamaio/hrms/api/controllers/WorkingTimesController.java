package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
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

import kodlamaio.hrms.business.abstracts.WorkingTimeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.WorkingTime;

@RestController
@RequestMapping("/api/workingtimes")
@CrossOrigin
public class WorkingTimesController {

	private WorkingTimeService workingTimeService;

	@Autowired
	public WorkingTimesController(WorkingTimeService workingTimeService) {
		this.workingTimeService = workingTimeService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody WorkingTime workingTime) {
		return ResponseEntity.ok(this.workingTimeService.add(workingTime));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody WorkingTime workingTime) {

		return ResponseEntity.ok(this.workingTimeService.update(workingTime));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody WorkingTime workingTime) {
		return this.workingTimeService.delete(workingTime);
	}

	@GetMapping("/getAll")
	public DataResult<List<WorkingTime>> getAll() {
		return this.workingTimeService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<WorkingTime> getById(@RequestParam int workingTimeId) {
		return this.workingTimeService.getById(workingTimeId);
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
