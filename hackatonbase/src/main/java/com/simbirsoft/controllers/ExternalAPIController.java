package com.simbirsoft.controllers;

import com.simbirsoft.core.Config;
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
@RequestMapping(value = "/api")
public class ExternalAPIController {
    public static final String baseHackatonUrl = "http://smarthome.simbirsoft:8080";
    private DeviceController deviceController = new DeviceController();

    @RequestMapping(value = "/controlPage", method = RequestMethod.GET)
    public String controlPage() {
        return Helper.getControlPage(deviceController);
    }

    @RequestMapping(value = "/autoRegime", method = RequestMethod.GET)
    public String autoRegime(@RequestParam("state") String state) {
        Config.isAutoRegimeOn = state.equals("ON") ? true : false;
        return Helper.getControlPage(deviceController);
    }

    @RequestMapping(value = "/light", method = RequestMethod.GET)
    public String controlLight(@RequestParam("state") String state) {
        try {
            deviceController.setLight(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return Helper.getControlPage(deviceController);
    }

    @RequestMapping(value = "/blinds", method = RequestMethod.GET)
    public String controlBlinds(@RequestParam("state") String state) {
        try {
            deviceController.setBlinds(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return Helper.getControlPage(deviceController);
    }

    @RequestMapping(value = "/conditioner", method = RequestMethod.GET)
    public String controlConditioner(@RequestParam("state") String state) {
        try {
            deviceController.setConditioner(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return Helper.getControlPage(deviceController);
    }

    @RequestMapping(value = "/heater", method = RequestMethod.GET)
    public String controlHeater(@RequestParam("state") String state) {
        try {
            deviceController.setHeater(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return Helper.getControlPage(deviceController);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String controlAll(@RequestParam("turnOff") String turnOff) {
        try {
            if (turnOff.equals("YES")) {
                Config.isAutoRegimeOn = false;
                deviceController.setConditioner("OFF");
                deviceController.setHeater("OFF");
                deviceController.setBlinds("OFF");
                deviceController.setLight("OFF");
            }
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return Helper.getControlPage(deviceController);
    }

    @RequestMapping(value = "/burglary", method = RequestMethod.GET)
    public String alarmBurglary() {
        try {
            Config.isAutoRegimeOn = false;
            deviceController.setConditioner("OFF");
            deviceController.setHeater("OFF");
            deviceController.setBlinds("OFF");
            deviceController.setLight("ON");

            URI policeURI = UriComponentsBuilder.fromHttpUrl(baseHackatonUrl)
                    .path("/services/POLICE")
                    .build()
                    .toUri();
            new RestTemplate().postForObject(policeURI, "de7684649e49c31023e33974995e81f2", String.class);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return Helper.getControlPage(deviceController);
    }
}
