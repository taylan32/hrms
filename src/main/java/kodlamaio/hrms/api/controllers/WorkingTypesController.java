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

import kodlamaio.hrms.business.abstracts.WorkingTypeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.WorkingType;

@RestController
@RequestMapping("/api/workingtypes")
public class WorkingTypesController {

	private WorkingTypeService workingTypeService;

	@Autowired
	public WorkingTypesController(WorkingTypeService workingTypeService) {
		this.workingTypeService = workingTypeService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody WorkingType workingType) {
		return ResponseEntity.ok(this.workingTypeService.add(workingType));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody WorkingType workingType) {
		return ResponseEntity.ok(this.workingTypeService.update(workingType));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody WorkingType workingType) {
		return this.workingTypeService.delete(workingType);
	}

	@GetMapping("/getAll")
	public DataResult<List<WorkingType>> getAll() {
		return this.workingTypeService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<WorkingType> getById(@RequestParam int workingTypeId) {
		return this.workingTypeService.getById(workingTypeId);
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
