package core.repository;

import core.domain.Sensor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorRepository extends Repository<Sensor, Integer> {
    @Query("select distinct s.name from Sensor s")
    List<String> getAllNames();

    List<Sensor> getDistinctTop4ByNameOrderByTimeDesc(String name);
}
