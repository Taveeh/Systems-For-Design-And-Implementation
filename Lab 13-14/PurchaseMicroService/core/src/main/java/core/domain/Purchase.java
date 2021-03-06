package core.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Date;

@Entity
public class Purchase extends BaseEntity<CustomerPurchasePrimaryKey> {
    public  Purchase(){
    }

    private int price;
    private Date dateAcquired;
    private int review;

    public Purchase(CustomerPurchasePrimaryKey customerPurchasePrimaryKey, int price, Date dateAcquired, int review) {
        this.setId(customerPurchasePrimaryKey);
        this.price = price;
        this.dateAcquired = dateAcquired;
        this.review = review;
    }

    public void setCatId(Long id) {
        getId().setCatId(id);
    }

    public void setCustomerId(Long id) {
        getId().setCustomerId(id);
    }
    public Long getCatId() {
        return getId().getCatId();
    }

    public Long getCustomerId() {
        return getId().getCustomerId();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public boolean equals(Object obj) {
        return obj instanceof Purchase && this.getId().equals(((Purchase) obj).getId());
    }


    @Override
    public String toString() {
        return super.toString() + " Purchase{" +
                "price=" + price +
                ", dateAcquired=" + dateAcquired +
                ", review=" + review +
                '}';
    }
}
