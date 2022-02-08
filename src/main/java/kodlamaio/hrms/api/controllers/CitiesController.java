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

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.City;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin
public class CitiesController {

	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody City city) {
		return ResponseEntity.ok(this.cityService.add(city));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody City city) {
		return ResponseEntity.ok(this.cityService.update(city));
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody City city) {
		return this.cityService.delete(city);
	}

	@GetMapping("/getAll")
	public DataResult<List<City>> getAll() {
		return this.cityService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<City> getById(@RequestParam int cityId) {
		return this.cityService.getById(cityId);
	}

	@GetMapping("/getByCityName")
	public DataResult<City> getByCityName(@RequestParam String cityName) {
		return this.cityService.getByCityName(cityName);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationExceptions = new HashMap<String, String>();
		exceptions.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			validationExceptions.put(fieldName, errorMessage);
		});
		return validationExceptions;
	}

}
