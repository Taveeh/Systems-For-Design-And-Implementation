package core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cat extends BaseEntity<Long> {
    String name, breed;
    Integer catYears;

    public Set<CatFood> getCatFoods() {
        return catFoods;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public Cat() {

    }

    public Toy getFavoriteToy() {
        return favoriteToy;
    }

    public void setFavoriteToy(Toy favoriteToy) {
        this.favoriteToy = favoriteToy;
    }

    public Cat(Long id, String name, String breed, Integer catYears) {
        this.setId(id);
        this.name = name;
        this.breed = breed;
        this.catYears = catYears;
    }


    @JsonBackReference(value = "cat-reference")
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "toy_id", referencedColumnName = "id")
    private Toy favoriteToy;

    @JsonManagedReference(value = "cat-reference")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cat", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    Set<CatFood> catFoods;

    public void setCatFoods(Set<CatFood> catFoods) {
        this.catFoods = catFoods;
    }

    @JsonManagedReference(value = "cat-reference")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cat", cascade = {CascadeType.REMOVE})
    Set<Purchase> purchases;

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Integer getCatYears() {
        return catYears;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setCatYears(Integer catYears) {
        this.catYears = catYears;
    }

    public Integer getHumanYears(){
        return catYears*15;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Cat{name: " + this.name +
                "; breed: " + this.breed +
                "; catYears: " + this.catYears +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cat && this.getId().equals(((Cat) obj).getId());
    }
}
