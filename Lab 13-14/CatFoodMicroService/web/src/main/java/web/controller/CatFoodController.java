package web.controller;

import core.domain.CatFood;
import core.service.CatFoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.CatFoodConverter;
import web.dto.CatFoodPrimaryKeyDTO;
import web.dto.CatFoodsDTO;

import java.util.List;

@RestController
public class CatFoodController {
    public static final Logger logger = LoggerFactory.getLogger(CatFoodController.class);

    @Autowired
    private CatFoodService catFoodService;

    @Autowired
    private CatFoodConverter catFoodConverter;

    @RequestMapping(value = "/catFoods")
    CatFoodsDTO getCatFoodFromRepository(){
        logger.trace("getCatFoodFromRepository - method entered");
        List<CatFood> catFoods = catFoodService.getCatFoodFromRepository();
        CatFoodsDTO catFoodsDTO = new CatFoodsDTO(catFoodConverter.convertModelsToDTOs(catFoods));
        logger.trace("getAllCatFoods: " + catFoods);
        return catFoodsDTO;
    }

    @PostMapping(value = "/catFoods", consumes = "application/x-www-form-urlencoded")
    void addCatFood( CatFoodPrimaryKeyDTO catFoodPrimaryKeyDTO){
        logger.trace("addCatFood - method entered - catFoodDTO: " + catFoodPrimaryKeyDTO);
        catFoodService.addCatFood(catFoodPrimaryKeyDTO.getCatId(), catFoodPrimaryKeyDTO.getFoodId());
        logger.trace("addCatFood - catFood added");
    }

    @PutMapping(value = "/catFoods/{newId}", consumes = "application/x-www-form-urlencoded")
    void updateCatFood(@PathVariable Long newId, CatFoodPrimaryKeyDTO catFoodDTO){
        logger.trace("updateCatFood - method entered - catFoodDTO: " + catFoodDTO);
        catFoodService.updateCatFood(catFoodDTO.getCatId(), catFoodDTO.getFoodId(), newId);
        logger.trace("updateCatFood - catFood updated");
    }

    @RequestMapping(value = "/catFoods/{catId}&{foodId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCatFood(@PathVariable Long catId, @PathVariable Long foodId) {
        logger.trace("deleteCatFood - method entered: catId: " + catId + ", foodId: " + foodId);
        catFoodService.deleteCatFood(catId, foodId);
        logger.trace("deleteCatFood - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/catFoods/{foodId}", method = RequestMethod.GET)
    CatFoodsDTO filterCatsThatEatCertainFood(@PathVariable Long foodId){
        logger.trace("filterCatsThatEatCertainFood - method entered");
        List<CatFood> catFoods = catFoodService.filterCatsThatEatCertainFood(foodId);
        CatFoodsDTO catFoodsDTO = new CatFoodsDTO(catFoodConverter.convertModelsToDTOs(catFoods));
        logger.trace("filterCatsThatEatCertainFood: " + catFoods);
        return catFoodsDTO;
    }
}
