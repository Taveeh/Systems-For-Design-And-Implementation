package web.dto;

import core.domain.Toy;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CatDTO extends BaseDTO<Long> {
    private String name, breed;
    private Integer catYears;
    Toy favoriteToy;

}
