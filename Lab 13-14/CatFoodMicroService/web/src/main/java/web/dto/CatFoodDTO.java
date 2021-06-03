package web.dto;

import core.domain.CatFoodPrimaryKey;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"cat", "food"})
@ToString(callSuper = true, exclude = {"cat", "food"})
@Builder
public class CatFoodDTO extends BaseDTO<CatFoodPrimaryKey>{
    Long cat, food;
}
