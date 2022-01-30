package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.City;

public interface CityDao extends JpaRepository<City, Integer>{
	
	
	City getByCityName(String cityName);
	
	@Query("select c from City c where c.id=:cityId")
	City getById(int cityId);
}
