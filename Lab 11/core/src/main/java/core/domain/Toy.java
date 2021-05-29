package core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Toy extends BaseEntity<Long> {
    String name;
    int size;

    public Toy(Long id, String name, int size) {
        this.setId(id);
        this.name = name;
        this.size = size;
    }

    @JsonBackReference(value = "cat-reference")
    @OneToOne(mappedBy = "favoriteToy")
    private Cat cat;

}
