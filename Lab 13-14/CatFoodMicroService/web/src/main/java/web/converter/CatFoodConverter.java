package web.converter;

import core.domain.CatFood;
import core.domain.CatFoodPrimaryKey;

import org.springframework.stereotype.Component;
import web.dto.CatFoodDTO;

@Component
public class CatFoodConverter extends BaseConverter<CatFoodPrimaryKey, CatFood, CatFoodDTO> {
    @Override
    public CatFood convertDtoToModel(CatFoodDTO dto) {
        var model = new CatFood();
        model.setId(dto.getId());
        model.setCatId(dto.getCat());
        model.setFoodId(dto.getFood());
        return model;
    }

    @Override
    public CatFoodDTO convertModelToDto(CatFood catFood) {
        var catFoodDto = new CatFoodDTO();
        catFoodDto.setId(catFood.getId());
        catFoodDto.setCat(catFood.getCatId());
        catFoodDto.setFood(catFood.getFoodId());
        return catFoodDto;
    }
}
