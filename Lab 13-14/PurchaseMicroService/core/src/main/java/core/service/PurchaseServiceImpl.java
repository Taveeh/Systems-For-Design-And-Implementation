package core.service;

import core.domain.*;
import core.exceptions.PetShopException;

import core.repository.PurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    public static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Override
    @Transactional
    public void addPurchase(Long catId, Long customerId, int price, Date dateAcquired, int review) {
        logger.trace("addPurchase - method entered - catId: " + catId + ", customerId: " + customerId + ", price: " + price + ", date acquired: " + dateAcquired + ", review: " + review);
        purchaseRepository.save(new Purchase(new CustomerPurchasePrimaryKey(customerId, catId), price, dateAcquired, review));
        logger.trace("addPurchase - method finished");

    }

    @Override
    public List<Purchase> getPurchasesFromRepository() {
        logger.trace("getPurchasesFromRepository - method entered");
        List<Purchase> purchases = purchaseRepository.findAll();
        logger.trace("getPurchasesFromRepository: " + purchases.toString());
        return purchases;
    }


    @Override
    @Transactional
    public void deletePurchase(Long catId, Long customerId) {
        logger.trace("delete purchase - method entered - catId: " + catId + ", customerId: " + customerId);
        purchaseRepository.deleteById(new CustomerPurchasePrimaryKey(customerId, catId));
        logger.trace("delete purchase - method finished");
    }

    @Override
    @Transactional
    public void updatePurchase(Long catId, Long customerId, int newReview) {
        logger.trace("updatePurchase - method entered - catId: " + catId + ", customerId: " + customerId + " ,newReview" + newReview);
        purchaseRepository.findById(new CustomerPurchasePrimaryKey(customerId, catId)).stream().findFirst().ifPresent(purchase -> purchase.setReview(newReview));
        logger.trace("updatePurchase - method finished");
    }

//    @Override
//    public Set<Customer> filterCustomersThatBoughtBreedOfCat(String breed) {
//
//        logger.trace("filterCustomersThatBoughBreedOfCat - method entered - breed: " + breed);
//
//        Set<Customer> customers = getPurchasesFromRepository().stream()
//                .filter(purchase -> purchase.getCat().getBreed().equals(breed))
//                .map(Purchase::getCustomer)
//                .collect(Collectors.toSet());
//        logger.trace("filterCustomersThatBoughBreedOfCat - method finished - " + customers);
//        return customers;
//    }

    @Override
    public List<Purchase> filterPurchasesWithMinStars(int minStars) {
        logger.trace("filterPurchasesWithMinStars - method entered - min stars: " + minStars);
        List<Purchase> purchases = getPurchasesFromRepository().stream()
                .filter(purchase -> purchase.getReview() >= minStars)
                .collect(Collectors.toList());
        logger.trace("filterPurchasesWithMinStars - method finished - " + purchases);
        return purchases;
    }

//    @Override
//    public List<Pair<Customer, Integer>> reportCustomersSortedBySpentCash() {
//        logger.trace("reportCustomersSortedBySpentCash - method entered");
//        List<Pair<Customer, Integer>> toReturn = customerRepository.getCustomersSortedSpentCashInterface().stream()
//                .map(obj -> new Pair<>(new Customer(((BigInteger) obj[0]).longValue(), (String) obj[1], (String) obj[2]), ((BigInteger) obj[3]).intValue()))
//                .collect(Collectors.toList());
//        logger.trace("reportCustomersSortedBySpentCash - method finished - " + toReturn);
//        return toReturn;
//    }
}
