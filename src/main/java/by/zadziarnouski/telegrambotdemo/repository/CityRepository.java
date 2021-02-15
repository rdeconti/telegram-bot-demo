package by.zadziarnouski.telegrambotdemo.repository;

import by.zadziarnouski.telegrambotdemo.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findCityByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);
}
