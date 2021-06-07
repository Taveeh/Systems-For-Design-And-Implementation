package web.controller;

import core.domain.Sensor;
import core.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.converter.SensorConverter;
import web.dto.SensorsDTO;

import java.util.List;
import java.util.Set;

@RestController
public class SensorController {
    @Autowired
    private CoreService coreService;

    @Autowired
    private SensorConverter sensorConverter;

    @RequestMapping("/sensors")
    SensorsDTO getSensorsFromRepository() {
        List<Sensor> sensors = coreService.getSensorsFromRepository();
        return new SensorsDTO(sensorConverter.convertModelsToDTOs(sensors));
    }

    @RequestMapping("/sensorsnames")
    List<String> getNamesFromRepository() {
        return coreService.getAllNames();
    }

    @RequestMapping("/sensors/{name}")
    SensorsDTO getSensorsByName(@PathVariable String name) {
        List<Sensor> sensors = coreService.getSensorsForName(name);
        return new SensorsDTO(sensorConverter.convertModelsToDTOs(sensors));
    }
}
