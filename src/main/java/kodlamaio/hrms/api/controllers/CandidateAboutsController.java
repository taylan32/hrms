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

import kodlamaio.hrms.business.abstracts.CandidateAboutService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.CandidateAbout;

@RestController
@RequestMapping("/api/candidateabouts")
@CrossOrigin
public class CandidateAboutsController {

	private CandidateAboutService candidateAboutService;

	@Autowired
	public CandidateAboutsController(CandidateAboutService candidateAboutService) {
		this.candidateAboutService = candidateAboutService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody CandidateAbout candidateAbout) {
		return ResponseEntity.ok(this.candidateAboutService.add(candidateAbout));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody CandidateAbout candidateAbout) {
		return this.candidateAboutService.delete(candidateAbout);

	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody CandidateAbout candidateAbout) {
		return ResponseEntity.ok(this.candidateAboutService.update(candidateAbout));
	}

	@GetMapping("/getOne")
	public DataResult<List<CandidateAbout>> getOne(@RequestParam("candidateId") int candidateId) {
		return this.candidateAboutService.getOne(candidateId);
	}
	
	@GetMapping("/getById")
	public DataResult<CandidateAbout> getById(@RequestParam int id){
		return this.candidateAboutService.getById(id);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationExceptions = new HashMap<String, String>();
		exceptions.getBindingResult().getAllErrors().forEach((error) -> {
			String errorField = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationExceptions.put(errorField, errorMessage);
		});

		return validationExceptions;
	}
	

}
