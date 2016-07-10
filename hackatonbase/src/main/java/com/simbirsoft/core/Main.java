package com.simbirsoft.core;

import com.simbirsoft.controllers.DeviceController;
import com.simbirsoft.controllers.HelloController;
import com.simbirsoft.controllers.SensorController;
import org.apache.tomcat.jni.Thread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        autoRegime();
    }

    public static void autoRegime() {
        SensorController sc = new SensorController();
        DeviceController dc = new DeviceController();
        String state;

        while (true) {
            if (Config.isAutoRegimeOn) {
                try {
                    System.out.print(Config.isAutoRegimeOn);
                    sc.getLuminance();
                    sc.getRoomTemperature();

                    state = Helper.controlAirCondition(DataCollector.sensorValues.get("ROOM_TEMPERATURE"));
                    dc.setConditioner(state);
                    state = Helper.controlHeater(DataCollector.sensorValues.get("ROOM_TEMPERATURE"));
                    dc.setHeater(state);

                    state = Helper.controlBlinds(DataCollector.sensorValues.get("LUMINANCE"));
                    dc.setBlinds(state);
                    state = Helper.controlLights(DataCollector.sensorValues.get("LUMINANCE"));
                    dc.setLight(state);

                    java.lang.Thread.sleep(10000);
                }
                catch (InterruptedException e) {

                }
            }
        }
    }
}
