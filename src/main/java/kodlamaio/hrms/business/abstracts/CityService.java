package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.City;

public interface CityService {

	Result add(City city);
	Result delete(City city);
	Result update(City city);
	DataResult<List<City>> getAll();
	DataResult<City> getById(int cityId);
	DataResult<City> getByCityName(String cityName);
	
}
