package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.entities.City;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;

	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Override
	public Result add(City city) {
		if (checkIfCityExist(city.getId())) {
			return new ErrorResult(Messages.cityExists);
		}
		this.cityDao.save(city);
		return new SuccessResult(Messages.cityAdded);
	}

	@Override
	public Result delete(City city) {
		if (!checkIfCityExist(city.getId())) {
			return new ErrorResult(Messages.cityNotFound);
		}
		this.cityDao.delete(city);
		return new SuccessResult(Messages.cityDeleted);
	}

	@Override
	public Result update(City city) {
		if (!checkIfCityExist(city.getId())) {
			return new ErrorResult(Messages.cityNotFound);
		}
		this.cityDao.save(city);
		return new SuccessResult(Messages.cityUpdated);
	}

	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(), Messages.cityListed);
	}

	@Override
	public DataResult<City> getById(int cityId) {
		if (!checkIfCityExist(cityId)) {
			return new ErrorDataResult<City>(Messages.cityNotFound);
		}
		return new SuccessDataResult<City>(this.cityDao.getById(cityId),Messages.cityListed);
	}

	@Override
	public DataResult<City> getByCityName(String cityName) {
		City temp = this.cityDao.getByCityName(cityName);
		if (temp == null) {
			return new ErrorDataResult<City>(Messages.cityNotFound);
		}
		return new SuccessDataResult<City>(this.cityDao.getByCityName(cityName), Messages.cityListed);
	}

	private boolean checkIfCityExist(int cityId) {
		return this.cityDao.existsById(cityId);
	}

}
