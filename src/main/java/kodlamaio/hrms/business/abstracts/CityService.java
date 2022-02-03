package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.entities.City;

public interface CityService extends BaseEntityService<City> {

	DataResult<City> getByCityName(String cityName);

}
