package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementConfirmationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.JobAdvertisementConfirmation;

@RestController
@RequestMapping("/api/jobadvertisementconfirmations")
public class JobAdvertisementConfirmationsController {

	private JobAdvertisementConfirmationService advertisementConfirmationService;

	@Autowired
	public JobAdvertisementConfirmationsController(
			JobAdvertisementConfirmationService advertisementConfirmationService) {
		this.advertisementConfirmationService = advertisementConfirmationService;
	}

	@PostMapping("/confirmAdvertisement")
	public Result add(@RequestBody JobAdvertisementConfirmation jobAdvertisementConfirmation) {
		return this.advertisementConfirmationService.add(jobAdvertisementConfirmation);
	}

	@GetMapping("/getByJobAdvertisementId")
	public DataResult<JobAdvertisementConfirmation> getByJobAdvertisement_Id(@RequestParam int jobAdvertisementId) {
		return this.advertisementConfirmationService.getByJobAdvertisement_Id(jobAdvertisementId);
	}

	@GetMapping("/getAll")
	public DataResult<List<JobAdvertisementConfirmation>> getAll() {
		return this.advertisementConfirmationService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<JobAdvertisementConfirmation> getById(@RequestParam int jobAdvertisementConfirmationId) {
		return this.advertisementConfirmationService.getById(jobAdvertisementConfirmationId);
	}

}
