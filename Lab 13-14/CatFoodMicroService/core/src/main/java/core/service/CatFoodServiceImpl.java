package core.service;

import core.domain.*;
import core.exceptions.PetShopException;
import core.repository.CatFoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatFoodServiceImpl implements CatFoodService {
    public static final Logger logger = LoggerFactory.getLogger(CatFoodServiceImpl.class);


    @Autowired
    private CatFoodRepository catFoodRepository;

    @Override
    public void addCatFood(Long catId, Long foodId) {
        logger.trace("add catFood - method entered - catId: " + catId + ", foodId: " + foodId);
        catFoodRepository.save(new CatFood(catId, foodId));
        logger.trace("add catFood - method finished");
    }

    @Override
    public List<CatFood> getCatFoodFromRepository() {
        logger.trace("getCatFoodFromRepository - method entered");
        List<CatFood> catFoods = catFoodRepository.findAll();
        logger.trace("getCatFoodFromRepository: " + catFoods.toString());
        return catFoods;
    }


    @Override
    public void deleteCatFood(Long catId, Long foodId) {
        logger.trace("deleteCatFood - method entered - catId: " + catId + " - foodId: " + foodId);
        catFoodRepository.findById(new CatFoodPrimaryKey(catId, foodId))
                .ifPresentOrElse(
                        catFood -> catFoodRepository.deleteById(catFood.getId()),
                        () -> {throw new PetShopException("Cat food does not exist");}
                );
        logger.trace("deleteCatFood - method finished");
    }

    @Override
    @Transactional
    public void updateCatFood(Long catId, Long foodId, Long newFoodId) {
        logger.trace("updateCatFood - method entered - catId: " + catId + " - foodId: " + foodId + " - newFoodId: " + newFoodId);
        deleteCatFood(catId, foodId);
        addCatFood(catId, newFoodId);
        logger.trace("updateCatFood - method finished");
    }


    @Override
    public List<CatFood> filterCatsThatEatCertainFood(Long foodId) {
        logger.trace("filterCatsThatEatCertainFood - method entered - foodId: " + foodId);

        List<CatFood> catFoods = catFoodRepository.findAll().stream().filter(catFood -> catFood.getFoodId().equals(foodId)).collect(Collectors.toList());
        logger.trace("filterCatsThatEatCertainFood: " + catFoods.toString());
        return catFoods;
    }
}
