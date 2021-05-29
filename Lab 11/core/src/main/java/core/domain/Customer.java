package core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Customer extends BaseEntity<Long>{
    String name;
    String phoneNumber;

    public Customer(){

    }

    public Customer(Long id, String name, String phoneNumber) {
        this.setId(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @JsonManagedReference(value = "customer-reference")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = {CascadeType.REMOVE})
    Set<Purchase> purchaseSet;

    public Set<Purchase> getPurchaseSet() {
        return purchaseSet;
    }

    public void setPurchaseSet(Set<Purchase> purchaseSet) {
        this.purchaseSet = purchaseSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return super.toString() + " Customer{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Customer && this.getId().equals(((Customer) obj).getId());
    }
}
