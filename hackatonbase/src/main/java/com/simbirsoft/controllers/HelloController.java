package com.simbirsoft.controllers;

import java.net.URI;

import com.simbirsoft.entity.User;
import com.simbirsoft.services.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    private static final String baseUrl = "http://localhost:8080/";
    private static final String baseHackatonUrl = "http://smarthome.simbirsoft:8080";

    @Autowired
    private HelloService hello;

    @RequestMapping(value = "/world", method = RequestMethod.GET)
    public String getHello() {
        return hello.getHello();
    }

    @RequestMapping(value = "/w", method = RequestMethod.GET)
    public String getH() {
        return hello.getHello();
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public String getHelloUser(@PathVariable String name) {
        return hello.getHelloUser(name);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        return hello.getUser();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String setUser(@RequestBody User user) {
        return hello.setUser(user);
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String getParam(@RequestParam("textLine") String textLine) {
        return textLine;
    }

    @RequestMapping(value = "/clientRequest", method = RequestMethod.GET)
    public String sendClientRequestToYourself() {
        URI targetUri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("hello/param")
                .queryParam("textLine", "My Param")
                .build()
                .toUri();
        return new RestTemplate().getForObject(targetUri, String.class);
    }

    @RequestMapping(value = "/sensorInfo", method = RequestMethod.GET)
    public String sendClientRequestToSensors(String sensor) {
        URI targetUri = UriComponentsBuilder.fromHttpUrl(baseHackatonUrl)
                .path("/sensors/types")
                .queryParam("type", "Pressure")
                .build()
                .toUri();
        return new RestTemplate().getForObject(targetUri, String.class);
    }
}