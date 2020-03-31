package com.sokolovskaya.travelbot.controller;

import com.sokolovskaya.travelbot.model.City;
import com.sokolovskaya.travelbot.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TravelController {

    private final TravelService travelService;

    @GetMapping(value = "/travelbot")
    public String getTravelBotPage() {

        return "travelbot";
    }

    @GetMapping(value = "/travel")
    @ResponseBody
    public ResponseEntity<List<City>> getAllCities() {

        return ResponseEntity.ok(travelService.getAll());
    }

    @PostMapping(value = "/travel_save")
    @ResponseBody
    public ResponseEntity<List<City>> saveNewCity(@RequestBody City city) {

        return ResponseEntity.ok(travelService.newCity(city));
    }

    @PostMapping(value = "/travel_update")
    @ResponseBody
    public ResponseEntity<List<City>> updateCity(@RequestBody City city) {

        return ResponseEntity.ok(travelService.updateCity(city));
    }

    @PostMapping(value = "/travel_delete")
    @ResponseBody
    public ResponseEntity<List<City>> deleteCity(@RequestBody City city) {

        return ResponseEntity.ok(travelService.deleteCity(city));
    }

    @PostMapping(value = "/travel_get")
    @ResponseBody
    public ResponseEntity<Optional<City>> getCity(@RequestBody City city) {

        return ResponseEntity.ok(travelService.getCityById(city));
    }
}