package kodlamaio.hrms.business.concretes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidatePhotoService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.adapters.abstracts.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidatePhotoDao;
import kodlamaio.hrms.entities.CandidatePhoto;

@Service
public class CandidatePhotoManager implements CandidatePhotoService {

	private CandidatePhotoDao candidatePhotoDao;
	private CloudinaryService cloudinaryService;

	@Autowired
	public CandidatePhotoManager(CandidatePhotoDao candidatePhotoDao, CloudinaryService cloudinaryService) {
		this.candidatePhotoDao = candidatePhotoDao;
		this.cloudinaryService = cloudinaryService;
	}

	@Override
	public Result upload(MultipartFile multipartFile, int candidateId) {

		try {
			BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
			if (bufferedImage == null)
				return new ErrorResult("Resim yuklenirken bir hata olustu.");
		} catch (IOException exception) {
			return new ErrorResult("Resim yuklenirken bir hata olustu. Acıklama: " + exception.getMessage());
		}

		if (!checkIfCandidatHasPhoto(candidateId)) { // fotoğraf ekle
			try {
				Map<?, ?> result = cloudinaryService.upload(multipartFile);
				CandidatePhoto candidatePhoto = new CandidatePhoto();
				candidatePhoto.getCandidate().setId(candidateId);
				candidatePhoto.setName((String) result.get("original_filename"));
				candidatePhoto.setUrl((String) result.get("url"));
				candidatePhoto.setPhotoId(Integer.valueOf((String) result.get("public_id")));
				this.candidatePhotoDao.save(candidatePhoto);

			} catch (IOException exception) {
				return new ErrorResult("Resim yuklenirken bir hata olustu. Acıklama: " + exception.getMessage());
			}
		} else { // adayın fotoğrafı var. mevcut olanı guncelle

			try {
				// mecvut olanı sil, yeniden ekle
				Map<?, ?> result = this.cloudinaryService.delete(candidateId);
				result = this.cloudinaryService.upload(multipartFile);
				CandidatePhoto candidatePhoto = this.candidatePhotoDao.getOneByCandidateId(candidateId);
				candidatePhoto.setName((String) result.get("original_filename"));
				candidatePhoto.setUrl((String) result.get("url"));
				candidatePhoto.setPhotoId(Integer.valueOf((String) result.get("public_id")));
				this.candidatePhotoDao.save(candidatePhoto);
				return new SuccessResult(Messages.photoAdded);
			} catch (IOException exception) {
				return new ErrorResult("Resim yuklenirken bir hata olustu. Acıklama: " + exception.getMessage());
			}

		}
		return new ErrorResult("Resim yuklenirken bir hata olustu. Acıklama");

	}

	@Override
	public Result delete(int photoId) {

		if (!this.candidatePhotoDao.existsById(photoId)) {
			return new ErrorResult(Messages.photoNotFound);
		}
		try {
			CandidatePhoto candidatePhoto = this.candidatePhotoDao.getById(photoId);
			@SuppressWarnings("unused")
			Map<?, ?> result = cloudinaryService.delete(photoId);
			candidatePhoto.setName(null);
			candidatePhoto.setPhotoId(0);
			candidatePhoto
					.setUrl("https://res.cloudinary.com/dqfj17jgm/image/upload/v1643757073/default-image_ztoug1.png");
			return new SuccessResult(Messages.photoDeleted);
		} catch (IOException exception) {
			return new ErrorResult("Resim yuklenirken bir hata olustu. Acıklama: " + exception.getMessage());

		}

	}

	@Override
	public DataResult<CandidatePhoto> getOneByCandidateId(int candidateId) {
		return new SuccessDataResult<CandidatePhoto>(this.candidatePhotoDao.getOneByCandidateId(candidateId));
	}

	@Override
	public DataResult<CandidatePhoto> getById(int candidatePhotoId) {
		if (this.candidatePhotoDao.existsById(candidatePhotoId))
			return new SuccessDataResult<CandidatePhoto>(this.candidatePhotoDao.getById(candidatePhotoId));
		else
			return new ErrorDataResult<CandidatePhoto>(Messages.photoNotFound);
	}

	private boolean checkIfCandidatHasPhoto(int candidateId) {
		var result = this.candidatePhotoDao.getOneByCandidateId(candidateId);
		if (result == null)
			return false;

		return true;
	}

}
