package com.simbirsoft.core;

import com.simbirsoft.entity.Sensor;

import java.util.HashMap;

import org.json.*;

/**
 * Created by Alex on 10.07.2016.
 */
public class Helper {
    // Парсер данных датчиков в словарь датчиков.
    public static HashMap<String, Sensor> parseSensorJsonToDictionary(String json) {
        HashMap<String, Sensor> sensorDict = new HashMap<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int minValue = jsonObject.getInt("minValue");
            int maxValue = jsonObject.getInt("maxValue");
            int step = jsonObject.getInt("step");
            String type = jsonObject.getString("type");
            Sensor sensor = new Sensor(minValue, maxValue, step, type);
            sensorDict.put(type, sensor);
        }
        return sensorDict;
    }

    public static String controlHeater(String roomTemperature) {
        double temperature = Double.parseDouble(roomTemperature);

        if (temperature < 15)
            return "ON";
        else
            return "OFF";
    }

    public static String controlAirCondition(String roomTemperature) {
        double temperature = Double.parseDouble(roomTemperature);

        if (temperature >= 30)
            return "ON";
        else
            return "OFF";
    }

    public static String controlBlinds(String luminance) {
        double value = Double.parseDouble(luminance);

        if (value > 200)
            return "ON";
        else
            return "OFF";
    }

    public static String controlLights(String luminance) {
        double value = Double.parseDouble(luminance);

        if (value < 100)
            return "ON";
        else
            return "OFF";
    }

}
