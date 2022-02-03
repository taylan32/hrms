package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;

import kodlamaio.hrms.core.utilities.results.SuccessDataResult;

@Service
public class UserManager implements UserService {

	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public DataResult<User> getById(int userId) {
		if (checkIfUserExists(userId)) {
			return new SuccessDataResult<User>(this.userDao.getByUserId(userId), Messages.userListed);
		}
		return new ErrorDataResult<User>(Messages.userExists);
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(), Messages.userListed);
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		User temp = this.userDao.getByEmail(email);
		if (temp == null) {
			return new ErrorDataResult<User>(Messages.userNotFound);
		}
		return new SuccessDataResult<User>(this.userDao.getByEmail(email), Messages.userListed);
	}

	private boolean checkIfUserExists(int userId) {
		return this.userDao.existsById(userId);
	}

}
