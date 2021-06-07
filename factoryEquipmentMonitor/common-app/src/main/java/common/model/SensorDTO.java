package common.model;

import lombok.*;

public class SensorDTO {
    int id;
    String name;
    int measurement;

    public SensorDTO(int id, String name, int measurement) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurement=" + measurement +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
