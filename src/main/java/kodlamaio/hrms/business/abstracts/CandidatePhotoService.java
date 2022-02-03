package kodlamaio.hrms.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.CandidatePhoto;

public interface CandidatePhotoService {

	Result upload(MultipartFile multipartFile, int candidateId);

	Result delete(int photoId);

	DataResult<CandidatePhoto> getOneByCandidateId(int candidateId);

	DataResult<CandidatePhoto> getById(int candidatePhotoId);

}
