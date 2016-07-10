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
                    java.lang.Thread.sleep(4000);
                    System.out.print(Config.isAutoRegimeOn);
                    sc.getLuminance();
                    sc.getRoomTemperature();

                    state = Helper.controlLights(DataCollector.sensorValues.get("ROOM_TEMPERATURE"));
                    dc.setConditioner(state);
                    dc.setHeater(state);

                    state = Helper.controlLights(DataCollector.sensorValues.get("LUMINANCE"));
                    dc.setBlinds(state);
                    dc.setLight(state);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
