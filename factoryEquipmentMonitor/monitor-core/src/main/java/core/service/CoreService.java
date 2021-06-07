package core.service;

import core.domain.Sensor;

import java.util.List;


public interface CoreService {
    List<Sensor> getSensorsFromRepository();

    List<String> getAllNames();

    List<Sensor> getSensorsForName(String name);
}
