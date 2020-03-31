package com.sokolovskaya.travelbot.service;

import com.sokolovskaya.travelbot.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BotService {

    private final TravelService travelService;
    private final String NOTFOUND = "Увы, по запросу ничего не найдено!";

    public String getMessage(String message) {

        String result;

        if (message.equals("/start")) {
            result = "Привет! " +
                    "\nЯ туристический бот. Просто напиши название города, " +
                    "в который собираешься отправиться, и я расскажу тебе о нем. ";
        } else {
            Optional<City> city = travelService.getCity(message);
            if (city.isPresent()) {
                result = city.get().getInformation();
            } else {
                result = NOTFOUND;
            }
        }

            return result;
    }
}