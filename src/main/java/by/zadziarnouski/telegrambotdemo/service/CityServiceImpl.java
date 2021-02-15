package by.zadziarnouski.telegrambotdemo.service;


import by.zadziarnouski.telegrambotdemo.model.City;
import by.zadziarnouski.telegrambotdemo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Method will save the city in the database, and if such a city already exists, it will update it.
     *
     * @param city a new city object.
     * @return saved or updated Object City
     * @throws IllegalArgumentException if the city is null.
     */
    @Override
    public City save(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City can not be null");
        }
        return cityRepository.save(city);
    }

    /**
     * Method gets all active cities from the database.
     *
     * @return list of all active cities as Object City.
     */
    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }


    /**
     * Method by name searches the database for a city.
     *
     * @param name a name of city.
     * @return a found city as Object City
     * @throws IllegalArgumentException if the name of city is null.
     */
    @Override
    public City findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of city can not be null");
        }
        return cityRepository.findCityByName(name);
    }

    /**
     * Method by id searches the database for a city.
     *
     * @param id a ID of city.
     * @return a found city as Object City
     * @throws IllegalArgumentException if ID is null.
     */
    @Override
    public City findById(long id) {

        Optional<City> byId = cityRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new IllegalArgumentException("ID can not be 0");
    }

    /**
     * Method by id delete a city from the database.
     *
     * @param id a ID of city.
     * @throws IllegalArgumentException if ID is 0.
     */
    @Override
    public void delete(long id) {
        if (id == 0) {
            throw new IllegalArgumentException("ID can not be 0");
        }
        cityRepository.deleteById(id);
    }

    /**
     * Method gets the names of all active cities and generates a list.
     *
     * @return list of activity cities as String
     */
    @Override
    public String getAllCityNames() {
        return cityRepository.findAll().stream().map(city -> city.getName() + '\n').collect(Collectors.joining());
    }

    /**
     * Method by name delete a city from the database.
     *
     * @param name a name of city.
     * @throws IllegalArgumentException if name of city is null.
     */
    @Override
    public void deleteByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of city can not be null");
        }
        cityRepository.deleteByName(name);
    }

    /**
     * Method .
     *
     * @param name a name of city.
     * @return true - if a city with such a name is in the database,
     * false - if not.
     * @throws IllegalArgumentException if name of city is null.
     */
    @Override
    public boolean existsByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of city can not be null");
        }
        return cityRepository.existsByName(name);
    }
}
