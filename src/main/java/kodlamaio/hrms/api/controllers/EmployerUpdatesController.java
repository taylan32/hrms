package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.EmployerUpdateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.EmployerUpdate;

@RestController
@RequestMapping("/api/employerupdates")
@CrossOrigin
public class EmployerUpdatesController {
	
	private EmployerUpdateService employerUdpateService;
	
	@Autowired
	public EmployerUpdatesController(EmployerUpdateService employerUpdateService) {
		this.employerUdpateService = employerUpdateService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody EmployerUpdate employerUpdate) {
		return this.employerUdpateService.add(employerUpdate);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody EmployerUpdate employerUpdate) {
		return this.employerUdpateService.update(employerUpdate);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody EmployerUpdate employerUpdate) {
		return this.employerUdpateService.delete(employerUpdate);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<EmployerUpdate>> getAll(){
		return this.employerUdpateService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<EmployerUpdate> getById(@RequestParam int id) {
		return this.employerUdpateService.getById(id);
	}
	
	@GetMapping("/getByEmployerId")
	public DataResult<EmployerUpdate> getByEmployerId(@RequestParam int employerId) {
		return this.employerUdpateService.getByEmployerId(employerId);
	}
	
}
