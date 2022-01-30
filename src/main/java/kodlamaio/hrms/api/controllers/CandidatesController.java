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

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Candidate;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {

	private CandidateService candidateService;

	@Autowired
	public CandidatesController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Candidate candidate) {
		//return ResponseEntity.ok(this.candidateService.add(candidate));
		System.out.println(candidate.getEmail());
		System.out.println(candidate.getFirstName());
		System.out.println(candidate.getUserId());
		return null;
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Candidate candidate) {
		return this.candidateService.delete(candidate);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Candidate candidate) {
		return ResponseEntity.ok(this.candidateService.update(candidate));
	}

	@GetMapping("/getAll")
	public DataResult<List<Candidate>> getAll() {
		return this.candidateService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<Candidate> getById(@RequestParam int candidateId) {
		return this.candidateService.getById(candidateId);
	}

	@GetMapping("/getByUserId")
	public DataResult<Candidate> getByUserId(@RequestParam int userId) {
		return this.candidateService.getByUserId(userId);
	}

	@GetMapping("/getByEmail")
	public DataResult<Candidate> getByEmail(@RequestParam String email) {
		return this.candidateService.getByEmail(email);
	}

	@GetMapping("/getByIndentityNumber")
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		return this.candidateService.getByIdentityNumber(identityNumber);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exceptions){
		
		Map<String, String> validationErrors = new HashMap<String,String>();
		exceptions.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationErrors.put(fieldName, errorMessage);
		});
		
		return validationErrors;
	}

}
