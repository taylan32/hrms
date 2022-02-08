package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import kodlamaio.hrms.business.abstracts.JobAdvertisementConfirmationTypeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobAdvertisementConfirmationType;

@RestController
@RequestMapping("/api/jobadvconfirmations")
@CrossOrigin
public class JobAdvertisementConfirmationTypesController {

	private JobAdvertisementConfirmationTypeService jobAdvertisementConfirmationTypeService;

	@Autowired
	public JobAdvertisementConfirmationTypesController(
			JobAdvertisementConfirmationTypeService jobAdvertisementConfirmationTypeService) {
		this.jobAdvertisementConfirmationTypeService = jobAdvertisementConfirmationTypeService;
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody JobAdvertisementConfirmationType jobAdvertisementConfirmationType) {
		return this.jobAdvertisementConfirmationTypeService.add(jobAdvertisementConfirmationType);
	}

	@PostMapping("/update")
	public Result update(@Valid @RequestBody JobAdvertisementConfirmationType jobAdvertisementConfirmationType) {
		return this.jobAdvertisementConfirmationTypeService.update(jobAdvertisementConfirmationType);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody JobAdvertisementConfirmationType jobAdvertisementConfirmationType) {
		return this.jobAdvertisementConfirmationTypeService.delete(jobAdvertisementConfirmationType);
	}

	@GetMapping("/getAll")
	public DataResult<List<JobAdvertisementConfirmationType>> getAll() {
		return this.jobAdvertisementConfirmationTypeService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<JobAdvertisementConfirmationType> getById(@RequestParam int jobAdvertisementConfirmationTypeId) {
		return this.jobAdvertisementConfirmationTypeService.getById(jobAdvertisementConfirmationTypeId);
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
