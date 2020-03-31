package com.sokolovskaya.travelbot.service;

import com.sokolovskaya.travelbot.model.City;
import com.sokolovskaya.travelbot.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TravelService {

    private final CityRepository cityRepository;

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public Optional<City> getCity(String cityName) {

        return cityRepository.findByName(cityName.toLowerCase().replace("-", " "));
    }

    public Optional<City> getCityById(City city) {

        return cityRepository.findById(city.getId());
    }

    public List<City> newCity(City city) {
        city.setName(city.getName().toLowerCase().replace("-", " "));
        cityRepository.save(city);

        return cityRepository.findAll();
    }

    public List<City> updateCity(City city) {
        cityRepository.updateCity(city.getName().toLowerCase().replace("-", " "),
                city.getInformation(), city.getId());

        return cityRepository.findAll();
    }

    public List<City> deleteCity(City city) {
        cityRepository.deleteCityById(city.getId());

        return cityRepository.findAll();
    }
}