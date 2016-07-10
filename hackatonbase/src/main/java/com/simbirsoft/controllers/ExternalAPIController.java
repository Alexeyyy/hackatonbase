package com.simbirsoft.controllers;

import com.simbirsoft.core.Config;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alex on 10.07.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class ExternalAPIController {
    private DeviceController deviceController = new DeviceController();

    @RequestMapping(value = "/autoRegime", method = RequestMethod.GET)
    public String autoRegime(@RequestParam("state") String state) {
        Config.isAutoRegimeOn = state.equals("ON") ? true : false;
        return "Success";
    }

    @RequestMapping(value = "/light", method = RequestMethod.GET)
    public String controlLight(@RequestParam("state") String state) {
        try {
            deviceController.setLight(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return "Success";
    }

    @RequestMapping(value = "/blinds", method = RequestMethod.GET)
    public String controlBlinds(@RequestParam("state") String state) {
        try {
            deviceController.setBlinds(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return "Success";
    }

    @RequestMapping(value = "/conditioner", method = RequestMethod.GET)
    public String controlConditioner(@RequestParam("state") String state) {
        try {
            deviceController.setConditioner(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return "Success";
    }

    @RequestMapping(value = "/heater", method = RequestMethod.GET)
    public String controlHeater(@RequestParam("state") String state) {
        try {
            deviceController.setHeater(state);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return "Success";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String controlAll(@RequestParam("turnOff") String turnOff) {
        try {
            if (turnOff.equals("YES")) {
                deviceController.setConditioner("OFF");
                deviceController.setHeater("OFF");
                deviceController.setBlinds("OFF");
                deviceController.setLight("OFF");
            }
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return "Success";
    }
}
