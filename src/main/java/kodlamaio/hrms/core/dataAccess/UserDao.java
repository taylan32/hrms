package kodlamaio.hrms.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.core.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User getByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.id = id")
	User getByUserId(int userId);

}
