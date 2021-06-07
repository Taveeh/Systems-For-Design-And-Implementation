package common;

import common.model.SensorDTO;

import java.util.concurrent.CompletableFuture;

public interface SensorService {
    int PORT = 1234;
    String HOST = "localhost";
    String SEND_SENSOR = "sendSensor";

    CompletableFuture<String> sendSensor(SensorDTO sensorDTO);
}
