package by.zadziarnouski.telegrambotdemo.service;



import by.zadziarnouski.telegrambotdemo.model.City;

import java.util.List;


public interface CityService {
    City save(City city);

    List<City> findAll();

    City findByName(String name);

    City findById(long id);

    void delete(long id);

    String getAllCityNames();

    void deleteByName(String name);

    boolean existsByName(String name);

}
