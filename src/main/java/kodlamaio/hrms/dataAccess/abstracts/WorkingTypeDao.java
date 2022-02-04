package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.WorkingType;

public interface WorkingTypeDao extends JpaRepository<WorkingType, Integer> {

}
