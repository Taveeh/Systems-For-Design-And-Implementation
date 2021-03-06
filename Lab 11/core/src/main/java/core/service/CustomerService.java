package core.service;

import core.domain.Customer;
import core.exceptions.PetShopException;
import core.exceptions.ValidatorException;

import java.util.List;

public interface CustomerService {
    /**
     * Saves the customer with the given attributes to the repository of customers
     *
     * @throws IllegalArgumentException if the given id is null.
     * @throws ValidatorException       if the cat entity is not valid.
     */
    void addCustomer(String name, String phoneNumber);

    /**
     * @return all customers from the repository
     */
    List<Customer> getCustomersFromRepository();

    /**
     * Deletes a customer based on it's id
     *
     * @param id - id of the customer to be deleted
     * @throws IllegalArgumentException if the given id is null.
     * @throws PetShopException         if the customer does not exist
     */
    void deleteCustomer(Long id);

    /**
     * @param id must not be null
     * @throws IllegalArgumentException if the given id is null.
     * @throws ValidatorException       if the customer entity is not valid.
     * @throws PetShopException         if the customer does not exist
     */
    void updateCustomer(Long id, String name, String phoneNumber);
}
