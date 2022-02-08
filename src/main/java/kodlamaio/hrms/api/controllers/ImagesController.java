package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Image;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImagesController {

	private ImageService imageService;

	@Autowired
	public ImagesController(ImageService imageService) {
		this.imageService = imageService;
	}

	@PostMapping("/delete")
	public Result delete(@RequestParam int imageId) {
		return this.imageService.delete(imageId);
	}

	@GetMapping("/getByUserId")
	public DataResult<Image> getByUserId(@RequestParam int userId) {
		return this.imageService.getByUserId(userId);
	}

	@PostMapping("/upload")
	public Result upload(@RequestBody MultipartFile file, @RequestParam int userId) {
		return this.imageService.upload(file, userId);
	}

}
