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

import kodlamaio.hrms.business.abstracts.SkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Skill;

@RestController
@RequestMapping("/api/skilld")
public class SkillsController {

	private SkillService skillService;

	@Autowired
	public SkillsController(SkillService skillService) {
		this.skillService = skillService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Skill skill) {
		return ResponseEntity.ok(this.skillService.add(skill));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Skill skill) {
		return ResponseEntity.ok(this.skillService.update(skill));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Skill skill) {
		return this.skillService.delete(skill);
	}

	@GetMapping("/getAll")
	public DataResult<List<Skill>> getAll(@RequestParam int candidateId) {
		return this.skillService.getAll(candidateId);
	}

	@GetMapping("/getById")
	public DataResult<Skill> getById(@RequestParam int skillId) {
		return this.skillService.getById(skillId);
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
