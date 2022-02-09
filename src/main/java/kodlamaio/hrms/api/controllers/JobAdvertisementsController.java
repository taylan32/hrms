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

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisements")
@CrossOrigin
public class JobAdvertisementsController {

	private JobAdvertisementService advertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		this.advertisementService = jobAdvertisementService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisement jobAdvertisement) {
		return ResponseEntity.ok(this.advertisementService.add(jobAdvertisement));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody JobAdvertisement jobAdvertisement) {
		return ResponseEntity.ok(this.advertisementService.update(jobAdvertisement));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.advertisementService.delete(jobAdvertisement);
	}

	@GetMapping("/getByEmployerId")
	public DataResult<List<JobAdvertisement>> getByEmployer_Id(@RequestParam int employerId) {
		return this.advertisementService.getByEmployer_Id(employerId);
	}

	@GetMapping("/geyByCityId")
	public DataResult<List<JobAdvertisement>> getByCity_Id(@RequestParam int cityId) {
		return this.advertisementService.getByCity_Id(cityId);
	}

	@GetMapping("/getByJobTitleId")
	public DataResult<List<JobAdvertisement>> getByJobTitle_Id(@RequestParam int jobTitleId) {
		return this.advertisementService.getByJobTitle_Id(jobTitleId);
	}

	@GetMapping("/getAllActiveAdvertisement")
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisement() {
		return this.advertisementService.getAllActiveJobAdvertisement();
	}

	@GetMapping("/getAllWaitingForConfirmation")
	public DataResult<List<JobAdvertisement>> getAllWaitingForConfirmation() {
		return this.advertisementService.getAllWaitingForConfirmation();
	}

	@GetMapping("/getAllActiveAdvertisementByEmployerId")
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementByEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getAllActiveJobAdvertisementByEmployerId(employerId);
	}

	@GetMapping("/getAllPassiveAdvertisementByEmployerId")
	public DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisementByEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getAllPassiveJobAdvertisementByEmployerId(employerId);
	}

	@GetMapping("/getAllActiveAdvertisementByCityId")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByCityId(@RequestParam int cityId) {
		return this.advertisementService.getAllActiveAdvertisementByCityId(cityId);
	}

	@GetMapping("/getAllActiveAdvertisementByJobTitleId")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByJobTitleId(@RequestParam int jobTitleId) {
		return this.advertisementService.getAllActiveAdvertisementByJobTitleId(jobTitleId);
	}

	@GetMapping("/getAllActiveAdvertisementSortedDESC")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedDESC() {
		return this.advertisementService.getAllActiveAdvertisementSortedDESC();
	}

	@GetMapping("/getAllActiveAdvertisementSortedASC")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedASC() {
		return this.advertisementService.getAllActiveAdvertisementSortedASC();
	}

	@GetMapping("/setIsActiveFalse")
	public Result setIsActiveFalse(@RequestParam int jobAdvertisementId) {
		return this.advertisementService.setIsActiveFalse(jobAdvertisementId);
	}

	@GetMapping("/setIsActiveTrue")
	public Result setIsActiveTrue(@RequestParam int jobAdvertisementId) {
		return this.advertisementService.setIsActiveTrue(jobAdvertisementId);
	}

	@GetMapping("/getAllActivePageable")
	public DataResult<List<JobAdvertisement>> getAllActiveByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
		return this.advertisementService.getAllActiveByPage(pageNo, pageSize);
	}

	@GetMapping("/getAllActiveAdvertisementSortedDESCTop6")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementSortedDESCTop6() {
		return this.advertisementService.getAllActiveAdvertisementSortedDESCTop6();
	}

	@GetMapping("/getAllActiveAdvertisementByEmployerIdSortedDESC")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementByEmployerIdSortedDESC(
			@RequestParam int employerId) {
		return this.advertisementService.getAllActiveAdvertisementByEmployerIdSortedDESC(employerId);
	}

	@GetMapping("/getAllActiveAdvertisementFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(
			@RequestParam int cityId, @RequestParam int jobTitleId, @RequestParam int workingTimeId,
			@RequestParam int workingTypeId) {
		return this.advertisementService.getAllActiveAdvertisementFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(
				cityId, jobTitleId, workingTimeId, workingTypeId);
	}

	@GetMapping("/getAllActiveAdvertisementPageableFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType")
	public DataResult<List<JobAdvertisement>> getAllActiveAdvertisementPageableFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(
			@RequestParam int cityId, @RequestParam int jobTitleId, @RequestParam int workingTimeId,
			@RequestParam int workingTypeId, @RequestParam int pageNo, @RequestParam int pageSize) {
		return this.advertisementService
				.getAllActiveAdvertisementPageableFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(cityId,
						jobTitleId, workingTimeId, workingTypeId, pageNo, pageSize);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExcepiton(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationExceptions = new HashMap<String, String>();
		exceptions.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationExceptions.put(fieldName, errorMessage);
		});
		return validationExceptions;
	}

}
