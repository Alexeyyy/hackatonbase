package com.simbirsoft.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 10.07.2016.
 */
public class Sensor {
    @JsonProperty
    public int minValue;
    @JsonProperty
    public int maxValue;
    @JsonProperty
    public int step;
    @JsonProperty
    public String type;

    @JsonCreator
    public Sensor(int minValue, int maxValue, int step, String type) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.step = step;
        this.type = type;
    }
}
