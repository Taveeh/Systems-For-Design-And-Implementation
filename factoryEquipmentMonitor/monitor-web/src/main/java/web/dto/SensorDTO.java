package web.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SensorDTO extends BaseDTO<Integer> {
    private String name;
    private int measurement;
    private Long time;
}
