package kodlamaio.hrms.core.adapters.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.adapters.abstracts.CloudinaryService;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryServiceAdapter implements CloudinaryService {
	
	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
			"cloud_name", "dqfj17jgm",
			"api_key", "166744328938278",
			"api_secret", "AkJbOTcCtxouCA1PNj1FjIhLBvU",
			"secure", true));

	@Override
	public Map<?, ?> upload(MultipartFile multipartFile) throws IOException {
		File file = convert(multipartFile);
		Map<?,?> result = cloudinary.uploader().upload(file,ObjectUtils.emptyMap());
		file.delete();
		return result;
	}

	@Override
	public Map<?, ?> delete(int photoId) throws IOException {
		Map<?, ?> result = cloudinary.uploader().destroy(String.valueOf(photoId),ObjectUtils.emptyMap());
		return result;
	}

	@Override
	public File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(multipartFile.getBytes());
		fileOutputStream.close();
		return file;
	}

	

}
