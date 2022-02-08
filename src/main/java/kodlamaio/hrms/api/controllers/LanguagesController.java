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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Language;

@RestController
@RequestMapping("/api/languages")
@CrossOrigin
public class LanguagesController {

	private LanguageService languageService;

	@Autowired
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Language language) {
		
		return ResponseEntity.ok(this.languageService.add(language));
	}

	@PostMapping("delete")
	public Result delete(@RequestBody Language language) {
		return this.languageService.delete(language);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Language language) {
		return ResponseEntity.ok(this.languageService.update(language));
	}

	@GetMapping("/getAllByCandidateId")
	public DataResult<List<Language>> getByCandidateId(int candidateId) {
		return this.languageService.getByCandidateId(candidateId);
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
