package com.simbirsoft.core;

import com.simbirsoft.controllers.DeviceController;
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

    public static String getControlPage(DeviceController dc) {
        StringBuilder s = new StringBuilder();
        s.append("<html> <head> <title>Панель управления</title> <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"> </head> <body> <h3>Панель управления</h3> <ul>");

        if (!Config.isAutoRegimeOn) {
            if (dc.getDeviceStatus("CONDITIONER").equals("\"ON\""))
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/conditioner?state=OFF\">Выключить кондиционер</a></li>");
            else
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/conditioner?state=ON\">Включить кондиционер</a></li>");

            if (dc.getDeviceStatus("HEATER").equals("\"ON\""))
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/heater?state=OFF\">Выключить обогреватель</a></li>");
            else
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/heater?state=ON\">Включить обогреватель</a></li>");

            if (dc.getDeviceStatus("LIGHT").equals("\"ON\""))
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/light?state=OFF\">Выключить свет</a></li>");
            else
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/light?state=ON\">Включить свет</a></li>");

            if (dc.getDeviceStatus("BLINDS").equals("\"ON\""))
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/blinds?state=OFF\">Открыть жалюзи</a></li>");
            else
                s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/blinds?state=ON\">Закрыть жалюзи</a></li>");

            s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/autoRegime?state=ON\">Включить автоматический режим</a></li>");
        }
        else
            s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/autoRegime?state=OFF\">Выключить автоматический режим</a></li>");

        s.append("<li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/all?turnOff=YES\">Выключить все</a></li>");
        s.append("</ul>");

        s.append("<h3>Особые ситуации</h3>");
        s.append("<ul><li><a target=\"_self\" href=\"http://192.168.30.145:8080/api/burglary\">Вызвать полицию и напугать вора</a></li></ul>");

        s.append("</body> </html>");

        return s.toString();
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
