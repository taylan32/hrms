package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;

public interface UserService {

	DataResult<User> getById(int userId);

	DataResult<List<User>> getAll();

	DataResult<User> getByEmail(String email);

}
