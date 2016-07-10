package com.simbirsoft.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.simbirsoft.controllers, com.simbirsoft.services")
public class Config {
    public static boolean isAutoRegimeOn = false;
}
