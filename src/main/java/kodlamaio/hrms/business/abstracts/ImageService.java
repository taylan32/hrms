package kodlamaio.hrms.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.Image;

public interface ImageService {

	Result add(Image image);
	Result update(Image image);
	Result delete(int imageId);
	Result upload(MultipartFile multipartFile, int userId);
	DataResult<Image> getByUserId(int userId);
	DataResult<Image> getById(int id);
	void uploadDefaultImage(User user);
	
	
}
