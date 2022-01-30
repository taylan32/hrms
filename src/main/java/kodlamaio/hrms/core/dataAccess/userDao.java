package kodlamaio.hrms.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.core.entities.User;

public interface userDao extends JpaRepository<User, Integer> {

	User getByEmail(String email);

}
