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

import kodlamaio.hrms.business.abstracts.FavouriteJobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.FavouriteJobAdvertisement;

@RestController
@RequestMapping("/api/favouritejobadvertisements")
@CrossOrigin
public class FavouriteJobAdvertisementsController {

	private FavouriteJobAdvertisementService favouriteJobAdvertisementService;

	@Autowired
	public FavouriteJobAdvertisementsController(FavouriteJobAdvertisementService favouriteJobAdvertisementService) {
		this.favouriteJobAdvertisementService = favouriteJobAdvertisementService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody FavouriteJobAdvertisement favouriteJobAdvertisement) {
		return this.favouriteJobAdvertisementService.add(favouriteJobAdvertisement);
	}

	@PostMapping("/update")
	public Result update(@RequestBody FavouriteJobAdvertisement favouriteJobAdvertisement) {
		return this.favouriteJobAdvertisementService.update(favouriteJobAdvertisement);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody FavouriteJobAdvertisement favouriteJobAdvertisement) {
		return this.favouriteJobAdvertisementService.delete(favouriteJobAdvertisement);
	}

	@GetMapping("/getAll")
	public DataResult<List<FavouriteJobAdvertisement>> getAll() {
		return this.favouriteJobAdvertisementService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<FavouriteJobAdvertisement> getById(@RequestParam int favouriteJobAdvertisementId) {
		return this.favouriteJobAdvertisementService.getById(favouriteJobAdvertisementId);
	}

	@GetMapping("/getByCandidateId")
	DataResult<List<FavouriteJobAdvertisement>> getByCandidate_Id(@RequestParam int candidateId) {
		return this.favouriteJobAdvertisementService.getByCandidate_Id(candidateId);
	}

	@GetMapping("/getByAdvertisementId")
	DataResult<List<FavouriteJobAdvertisement>> getByJobAdvertisement_Id(@RequestParam int jobAdvertismentId) {
		return this.favouriteJobAdvertisementService.getByJobAdvertisement_Id(jobAdvertismentId);
	}

}
