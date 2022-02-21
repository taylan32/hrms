package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface UserService {

	DataResult<User> getById(int userId);

	DataResult<List<User>> getAll();

	DataResult<User> getByEmail(String email);

	Result login(String email, String password);
	
}
