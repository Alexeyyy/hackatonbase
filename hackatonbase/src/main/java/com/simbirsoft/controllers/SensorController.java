package com.simbirsoft.controllers;

import com.simbirsoft.core.DataCollector;
import com.simbirsoft.core.Helper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by Alex on 10.07.2016.
 */

@RestController
@RequestMapping(value = "/sensor")
public class SensorController {
    private static final String baseHackatonUrl = "http://smarthome.simbirsoft:8080";

    @RequestMapping(value = "/sensorData", method = RequestMethod.GET)
    public String getSensorData() {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseHackatonUrl)
                .path("/sensors/types")
                .queryParam("type", "Pressure")
                .build()
                .toUri();
        String json = new RestTemplate().getForObject(uri, String.class);
        DataCollector.sensorData = Helper.parseSensorJsonToDictionary(json);
        return json;
    }

    // Температура в комнате.
    @RequestMapping (value = "/getRoomTemperature", method = RequestMethod.GET)
    public String getRoomTemperature() {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseHackatonUrl)
                .path("/sensors/ROOM_TEMPERATURE")
                .build()
                .toUri();

        String temperature = new RestTemplate().getForObject(uri, String.class);
        DataCollector.sensorValues.put("ROOM_TEMPERATURE", temperature);

        return DataCollector.sensorValues.get("ROOM_TEMPERATURE");
    }

    // Уровень освещенности в комнате.
    @RequestMapping (value = "/getLuminance", method = RequestMethod.GET)
    public String getLuminance() {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseHackatonUrl)
                .path("/sensors/LUMINANCE")
                .build()
                .toUri();

        String temperature = new RestTemplate().getForObject(uri, String.class);
        DataCollector.sensorValues.put("LUMINANCE", temperature);

        return DataCollector.sensorValues.get("LUMINANCE");
    }
}
