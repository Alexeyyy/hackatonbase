package com.simbirsoft.controllers;

import java.net.URI;

import com.simbirsoft.core.DataCollector;
import com.simbirsoft.core.Helper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Alex on 10.07.2016.
 */
@RestController
@RequestMapping(value = "/devices")
public class DeviceController {
    private static final String baseUrl = "http://smarthome.simbirsoft:8080";
    private static final String microserviceId = "de7684649e49c31023e33974995e81f2";

    @RequestMapping(value = "/conditioner", method = RequestMethod.GET)
    public void setConditioner() {
        String state = Helper.controlAirCondition(DataCollector.sensorValues.get("ROOM_TEMPERATURE"));
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("devices/CONDITIONER")
                .queryParam("microserviceId", microserviceId)
                .queryParam("state",state)
                .build()
                .toUri();
        new RestTemplate().put(uri, null);
    }

    @RequestMapping(value = "/heater", method = RequestMethod.GET)
    public void setHeater() {
        String state = Helper.controlHeater(DataCollector.sensorValues.get("ROOM_TEMPERATURE"));
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("devices/HEATER")
                .queryParam("microserviceId", microserviceId)
                .queryParam("state",state)
                .build()
                .toUri();
        new RestTemplate().put(uri, null);
    }

    @RequestMapping(value = "/light", method = RequestMethod.GET)
    public void setLight() {
        String state = Helper.controlLights(DataCollector.sensorValues.get("LUMINANCE"));
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("devices/LIGHT")
                .queryParam("microserviceId", microserviceId)
                .queryParam("state",state)
                .build()
                .toUri();
        new RestTemplate().put(uri, null);
    }

    @RequestMapping(value = "/blinds", method = RequestMethod.GET)
    public void setBlinds() {
        String state = Helper.controlBlinds(DataCollector.sensorValues.get("LUMINANCE"));
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("devices/BLINDS")
                .queryParam("microserviceId", microserviceId)
                .queryParam("state",state)
                .build()
                .toUri();
        new RestTemplate().put(uri, null);
    }
}