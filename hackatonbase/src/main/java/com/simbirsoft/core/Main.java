package com.simbirsoft.core;

import com.simbirsoft.controllers.DeviceController;
import com.simbirsoft.controllers.HelloController;
import com.simbirsoft.controllers.SensorController;
import org.apache.tomcat.jni.Thread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        autoRequest();
    }

    public static void autoRequest() {
        SensorController sc = new SensorController();
        DeviceController dc = new DeviceController();

        while (true) {
            try {
                java.lang.Thread.sleep(4000);
                sc.getLuminance();
                sc.getRoomTemperature();

                dc.setBlinds();
                dc.setConditioner();
                dc.setHeater();
                dc.setLight();
            }
            catch (InterruptedException e) { }
        }
    }
}
