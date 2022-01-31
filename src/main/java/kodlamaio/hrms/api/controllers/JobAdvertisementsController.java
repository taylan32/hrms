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

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobAdvertisement;
import kodlamaio.hrms.entities.DTOs.JobAdvertisementDto;

@RestController
@RequestMapping("/api/jobadvertisements")
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

	@PostMapping("/delete")
	public Result delete(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.advertisementService.delete(jobAdvertisement);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody JobAdvertisement jobAdvertisement) {
		return ResponseEntity.ok(this.advertisementService.update(jobAdvertisement));
	}

	@GetMapping("/getAll")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.advertisementService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<JobAdvertisement> getById(@RequestParam int jobAdvertisementId) {
		return this.advertisementService.getById(jobAdvertisementId);
	}

	@GetMapping("/getByEmployerId")
	public DataResult<List<JobAdvertisement>> getByEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getByEmployerId(employerId);
	}

	@GetMapping("/getByJobTitleId")
	public DataResult<List<JobAdvertisement>> getByJobTitleId(@RequestParam int jobTitleId) {
		return this.advertisementService.getByJobTitleId(jobTitleId);
	}

	@GetMapping("/getByCityId")
	public DataResult<List<JobAdvertisement>> getByCityId(@RequestParam int cityId) {
		return this.advertisementService.getByCityId(cityId);
	}

	@GetMapping("/getllAllActive")
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisements() {
		return this.advertisementService.getAllActiveJobAdvertisements();
	}

	@GetMapping("/getAllPassive")
	public DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisements() {
		return this.advertisementService.getAllPassiveJobAdvertisements();
	}

	@GetMapping("/getAllActiveByEmployerId")
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsByEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getAllActiveJobAdvertisementsByEmployerId(employerId);
	}

	@GetMapping("/getAllPassiveByEmployerId")
	public DataResult<List<JobAdvertisement>> getAllPassiveJobAdvertisementsByEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getAllPassiveJobAdvertisementsByEmployerId(employerId);
	}

	@GetMapping("/setIsActiceFalse")
	public Result setIsActiveFalse(@RequestParam int jobAdvertisementId) {
		return this.advertisementService.setIsActiveFalse(jobAdvertisementId);
	}

	@GetMapping("/setIsActiveTrue")
	public Result setIsActiveTrue(@RequestParam int jobAdvertisementId) {
		return this.advertisementService.setIsActiveTrue(jobAdvertisementId);
	}

	@GetMapping("/getAllActiveWithDetails")
	public DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetail(){
		return this.advertisementService.getAllActiveJobAdvertisementWithDetail();
	}
	
	@GetMapping("/getAllPassiveWithDetail")
	public DataResult<List<JobAdvertisementDto>> getAllPassiveJobAdvertisementWithDetail(){
		return this.advertisementService.getAllPassiveJobAdvertisementWithDetail();
	}
	
	@GetMapping("/getAllWithDetail")
	public DataResult<List<JobAdvertisementDto>> getAllJobAdvertisementWithDetail(){
		return this.advertisementService.getAllJobAdvertisementWithDetail();
	}
	
	@GetMapping("/getAllActiveWithDetailByEmployerId")
	public DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetailByEmployerId(@RequestParam int employerId){
		return this.advertisementService.getAllActiveJobAdvertisementWithDetailByEmployerId(employerId);
	}
	
	@GetMapping("/getAllPassiveWithDetailByEmployerId")
	public DataResult<List<JobAdvertisementDto>> getAllPassiveJobAdvertisementWithDetailByEmployerId(int employerId){
		return this.advertisementService.getAllPassiveJobAdvertisementWithDetailByEmployerId(employerId);	
	}
	
	@GetMapping("/getAllActiveWithDetailASC")
	public DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetailSortedASC(){
		return this.advertisementService.getAllActiveJobAdvertisementWithDetailSortedASC();
	}
	
	@GetMapping("/getAllActiveWithDetailDESC")
	DataResult<List<JobAdvertisementDto>> getAllActiveJobAdvertisementWithDetailSortedDESC(){
		return this.advertisementService.getAllActiveJobAdvertisementWithDetailSortedDESC();
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationExceptions = new HashMap<String, String>();
		exceptions.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationExceptions.put(fieldName, errorMessage);
		});
		return validationExceptions;
	}

}
