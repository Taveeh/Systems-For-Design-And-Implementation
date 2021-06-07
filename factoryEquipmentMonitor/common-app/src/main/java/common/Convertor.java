package common;

import common.model.SensorDTO;

import java.util.Arrays;
import java.util.List;

public class Convertor {
    public static SensorDTO extractSensor(String dataTransferObject) {
        List<String> tokens = Arrays.asList(dataTransferObject.split(","));
        int id = Integer.parseInt(tokens.get(0));
        String name = tokens.get(1);
        int measurement = Integer.parseInt(tokens.get(2));
        return new SensorDTO(id, name, measurement);
    }

    public static String convertSensor(SensorDTO sensorDTO) {
        return sensorDTO.getId() + "," + sensorDTO.getName() + "," + sensorDTO.getMeasurement();
    }
}
