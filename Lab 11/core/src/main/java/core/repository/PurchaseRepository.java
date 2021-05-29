package core.repository;

import core.domain.CustomerPurchasePrimaryKey;
import core.domain.Purchase;

import java.util.List;

public interface PurchaseRepository extends Repository<Purchase, CustomerPurchasePrimaryKey> {
        List<Purchase> findAllByCat_Breed(String breed);
        List<Purchase> findAllByReviewGreaterThanEqual(Integer minReview);
}
