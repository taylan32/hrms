package kodlamaio.hrms.business.concretes;

import java.io.IOException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.cloudinary.CloudinaryService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ImageDao;
import kodlamaio.hrms.entities.Image;

@Service
public class ImageManager implements ImageService {

	private ImageDao imageDao;
	private CloudinaryService cloudinaryService;
	private UserService userService;

	@Autowired
	public ImageManager(ImageDao imageDao, CloudinaryService cloudinaryService, UserService userService) {
		this.imageDao = imageDao;
		this.cloudinaryService = cloudinaryService;
		this.userService = userService;
	}

	@Override
	public Result add(Image image) {
		this.imageDao.save(image);
		return new SuccessResult("Resim eklendi");
	}

	@Override
	public Result update(Image image) {
		this.imageDao.save(image);
		return new SuccessResult("Resim guncellendi");
	}

	@Override
	public Result delete(int imageId) {
		Image image = this.getById(imageId).getData();

		String[] splitImageUrl = image.getUrl().split("/");

		int index = splitImageUrl[splitImageUrl.length - 1].indexOf(".");
		String publicId = splitImageUrl[splitImageUrl.length - 1].substring(0, index);

		try {
			this.cloudinaryService.delete(Integer.valueOf(publicId));
		} catch (NumberFormatException | IOException exception) {
			return new ErrorResult(exception.getMessage());
		}

		return new SuccessResult("Resim silindi");

	}

	@Override
	public DataResult<Image> getById(int id) {
		return new SuccessDataResult<Image>(this.imageDao.getById(id));
	}

	@Override
	public Result upload(MultipartFile multipartFile, int userId) {
		try {
			Map<?, ?> uploadImage = (Map<?, ?>) this.cloudinaryService.upload(multipartFile);
			Image image = new Image();
			image.setUser(this.userService.getById(userId).getData());
			image.setUrl(uploadImage.get("url").toString());
			return add(image);
		} catch (IOException exception) {
			return new ErrorResult(exception.getMessage());
		}

	}

	@Override
	public DataResult<Image> getByUserId(int userId) {
		return new SuccessDataResult<Image>(this.imageDao.getByUser_Id(userId));
	}
	
	@Override
	public void uploadDefaultImage(User user) {
		Image image = new Image();
		image.setUser(user);
		image.setUrl("https://res.cloudinary.com/dqfj17jgm/image/upload/v1643757073/default-image_ztoug1.png");
		add(image);
	}

}
