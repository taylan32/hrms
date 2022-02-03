package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidatePhotoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.CandidatePhoto;

@RestController
@RequestMapping("/api/candidatephotos")
public class CandidatePhotosController {
	
	private CandidatePhotoService candidatePhotoService;

	@Autowired
	public CandidatePhotosController(CandidatePhotoService candidatePhotoService) {
		this.candidatePhotoService = candidatePhotoService;
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(MultipartFile multipartFile, @RequestParam int candidateId) {
		Result result = this.candidatePhotoService.upload(multipartFile, candidateId);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int photoId) {
		Result result = this.candidatePhotoService.delete(photoId);

		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);

	}

	@GetMapping("/getByCandidateId")
	public DataResult<CandidatePhoto> getOneByCandidateId(@RequestParam int candidateId) {
		return this.candidatePhotoService.getOneByCandidateId(candidateId);
	}

	@GetMapping("/getById")
	public DataResult<CandidatePhoto> getById(@RequestParam int candidatePhotoId) {
		return this.candidatePhotoService.getById(candidatePhotoId);
	}

}
