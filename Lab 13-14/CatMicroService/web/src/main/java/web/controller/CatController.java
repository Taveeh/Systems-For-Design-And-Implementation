package web.controller;


import core.domain.Cat;
import core.service.CatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.CatConverter;
import web.dto.CatDTO;
import web.dto.CatsDTO;

import java.util.List;

@RestController
public class CatController {
    public static final Logger logger = LoggerFactory.getLogger(CatController.class);

    @Autowired
    private CatService catService;

    @Autowired
    private CatConverter catConverter;

    @RequestMapping(value = "/cats", method = RequestMethod.GET)
    CatsDTO getCatsFromRepository() {
        System.out.println("WOAAH WE'RE HALFWAY THERE - BUT IN JAVA");
        logger.trace("getAllCats - method entered");
        List<Cat> cats = catService.getCatsFromRepository();
        CatsDTO catsDTO = new CatsDTO(catConverter.convertModelsToDTOs(cats));
        logger.trace("getAllCats: " + cats);
        return catsDTO;
    }

    @PostMapping(value = "/cats", consumes = "application/x-www-form-urlencoded")
    void addCat(CatDTO catDTO) {
        logger.trace("addCat - method entered - catDTO: " + catDTO);
        var cat = catConverter.convertDtoToModel(catDTO);
        catService.addCat(
                cat.getName(),
                cat.getBreed(),
                cat.getCatYears()
        );
        logger.trace("addCat - cat added");
    }

    @PutMapping(value = "/cats/{id}", consumes = "application/x-www-form-urlencoded")
    void updateCat(@PathVariable Long id, CatDTO catDTO) {
        logger.trace("updateCat - method entered - catDTO: " + catDTO);
        var cat = catConverter.convertDtoToModel(catDTO);
        catService.updateCat(
                id,
                cat.getName(),
                cat.getBreed(),
                cat.getCatYears()
        );
        logger.trace("updateCat - cat updated");
    }

    @RequestMapping(value = "/cats/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCat(@PathVariable Long id) {
        logger.trace("deleteCat - method entered - catId: " + id);
        catService.deleteCat(id);
        logger.trace("deleteCat - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
