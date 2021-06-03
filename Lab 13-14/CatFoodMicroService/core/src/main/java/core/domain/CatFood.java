package core.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CatFood extends BaseEntity<CatFoodPrimaryKey>{
    public CatFood(){
    }


    public CatFood(Long catId, Long foodId) {
        this.setId(new CatFoodPrimaryKey(catId, foodId));
    }

    public Long getCatId() {
        return this.getId().getCatId();
    }

    public Long getFoodId() {
        return this.getId().getFoodId();
    }

    public void setCatId(Long catId){
        this.getId().setCatId(catId);
    }

    public void setFoodId(Long foodId){
        this.getId().setFoodId(foodId);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CatFood && this.getId().equals(((CatFood) obj).getId());
    }

}

