package web.dto;

import core.domain.Cat;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToyDTO extends BaseDTO<Long> {
    private String name;
    int size;
    Cat cat;
}
