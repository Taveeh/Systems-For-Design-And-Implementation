package web.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorsDTO {
    Set<SensorDTO> sensors;
}
