package web.dto;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
@Builder
public class CustomerSpentCashDTO implements Serializable {
    Integer totalCash;
}
