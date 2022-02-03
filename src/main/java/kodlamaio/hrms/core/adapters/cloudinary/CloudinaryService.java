package kodlamaio.hrms.core.adapters.cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

	Map<?, ?> upload(MultipartFile multipartFile) throws IOException;

	//Map<?, ?> delete(String photoId) throws IOException;
	Map<?, ?> delete(int photoId) throws IOException;


	File convert(MultipartFile multipartFile) throws IOException;

}
