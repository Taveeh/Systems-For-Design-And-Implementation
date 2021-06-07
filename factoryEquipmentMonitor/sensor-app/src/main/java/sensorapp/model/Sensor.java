package sensorapp.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Sensor {
    int id;
    String name;
    int lowerBound;
    int upperBound;
}
