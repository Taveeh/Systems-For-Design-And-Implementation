package core.service;

import core.domain.Sensor;
import core.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreServiceImpl implements CoreService {
    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public List<Sensor> getSensorsFromRepository() {
        return sensorRepository.findAll();
    }

    @Override
    public List<String> getAllNames() {
        return sensorRepository.getAllNames();
    }

    @Override
    public List<Sensor> getSensorsForName(String name) {
        return sensorRepository.getDistinctTop4ByNameOrderByTimeDesc(name);
    }

}
