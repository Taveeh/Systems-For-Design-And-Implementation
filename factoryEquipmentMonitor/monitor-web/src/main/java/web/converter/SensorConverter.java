package web.converter;

import core.domain.Sensor;
import org.springframework.stereotype.Component;
import web.dto.SensorDTO;

@Component
public class SensorConverter extends BaseConverter<Integer, Sensor, SensorDTO> {
    @Override
    public Sensor convertDtoToModel(SensorDTO dto) {
        var model = new Sensor();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setMeasurement(dto.getMeasurement());
        model.setTime(dto.getTime());
        return model;
    }

    @Override
    public SensorDTO convertModelToDto(Sensor sensor) {
        var sensorDto = new SensorDTO(sensor.getName(), sensor.getMeasurement(), sensor.getTime());
        sensorDto.setId(sensor.getId());
        return sensorDto;
    }
}
