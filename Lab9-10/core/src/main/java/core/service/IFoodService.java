package core.service;

import core.domain.Food;
import core.exceptions.PetShopException;
import core.exceptions.ValidatorException;

import java.util.Date;
import java.util.List;

public interface IFoodService {
    /**
     * Saves the food with the given attributes to the food repository.
     *
     * @throws IllegalArgumentException if the given id is null.
     * @throws ValidatorException       if the food entity is not valid.
     */

    void addFood(String name, String producer, Date expirationDate);
    /**
     * @return all the food from the repository.
     */

    List<Food> getFoodFromRepository();

    /**
     * Deletes a food based on it's id
     *
     * @param id - id of the food to be deleted
     * @throws IllegalArgumentException if the given id is null.
     * @throws PetShopException         if the food does not exist
     *                                  if the food is currently eaten
     */
    void deleteFood(Long id);

    /**
     * Updates the food with the given attributes.
     *
     * @param id must not be null
     * @throws IllegalArgumentException if the given id is null.
     * @throws ValidatorException       if the food entity is not valid.
     */
    void updateFood(Long id, String name, String producer, Date expirationDate);

    List<Food> getNotExpiredFood();
}
