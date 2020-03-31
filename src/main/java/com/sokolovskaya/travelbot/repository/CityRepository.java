package com.sokolovskaya.travelbot.repository;

import com.sokolovskaya.travelbot.model.City;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findAll();

    Optional<City> findByName(String name);

    Optional<City> findById(Long id);

    City save(@NonNull City city);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update travel_storage.city set name = :name, information =:information " +
            "where id = :id ", nativeQuery = true)
    void updateCity(@Param("name") String name, @Param("information") String information, @Param("id") Long id);

    void deleteCityById(Long id);
}