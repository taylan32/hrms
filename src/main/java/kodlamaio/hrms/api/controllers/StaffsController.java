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

import kodlamaio.hrms.business.abstracts.StaffService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Staff;

@RestController
@RequestMapping("/api/staffs")
@CrossOrigin
public class StaffsController {

	private StaffService staffService;

	@Autowired
	public StaffsController(StaffService staffService) {
		this.staffService = staffService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Staff staff) {
		return ResponseEntity.ok(this.staffService.add(staff));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Staff staff) {
		return ResponseEntity.ok(this.staffService.update(staff));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Staff staff) {
		return this.staffService.delete(staff);
	}

	@GetMapping("/getAll")
	public DataResult<List<Staff>> getAll() {
		return this.staffService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<Staff> getById(@RequestParam int staffId) {
		return this.staffService.getById(staffId);
	}

	
	@GetMapping("/confirmAdvertisement")
	public Result confirmAdvertisement(@RequestParam int advertisementId) {
		return this.staffService.confirmAdvertisement(advertisementId);
	}
	
	@GetMapping("/confirmEmployer")
	public Result confirmEmployer(@RequestParam int employerId) {
		return this.staffService.confirmEmployer(employerId);
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
