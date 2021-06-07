package core.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
public class Sensor extends BaseEntity<Integer> {
    private String name;
    private int measurement;
    private Long time;

    public Sensor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMeasurement() {
        return measurement;
    }

    public void setMeasurement(int measurement) {
        this.measurement = measurement;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Sensor(Integer id, String name, int measurement, Long time) {
        this.setId(id);
        this.name = name;
        this.measurement = measurement;
        this.time = time;
    }
}
