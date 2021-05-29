package core.service;

import core.domain.Cat;
import core.exceptions.PetShopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import core.repository.ICatFoodRepository;
import core.repository.ICatRepository;
import core.repository.IPurchaseRepository;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Math.max;

import java.util.List;

@Service
public class CatServiceImpl implements ICatService {
    public static final Logger logger = LoggerFactory.getLogger(CatServiceImpl.class);

    @Autowired
    private ICatRepository catsRepository;

    @Override
    public void addCat(String name, String breed, Integer catYears) {
        logger.trace("add cat - method entered - name: " + name + ", breed: " + breed + ", catYears: " + catYears);
        long id = 0;
        for (Cat cat : this.catsRepository.findAll())
            id = max(id, cat.getId() + 1);
        Cat catToBeAdded = new Cat(id, name, breed, catYears);
        catsRepository.save(catToBeAdded);
        logger.trace("add cat - method finished");
    }

    @Override
    public List<Cat> getCatsFromRepository() {
        logger.trace("getCatsFromRepository - method entered");
        List<Cat> cats = catsRepository.findAll();
        logger.trace("getCatsFromRepository: " + cats.toString());
        return cats;
    }


    @Override
    public void deleteCat(Long id) {
        logger.trace("deleteCat - method entered - id: " + id);
        /*catFoodRepository.findAll().stream()
                .filter(catFood -> catFood.getCatId().equals(id))
                .findAny()
                .ifPresent((catFood) -> {
                    throw new PetShopException("Cat is currently fed");
                });
        StreamSupport.stream(purchaseRepository.findAllEntities().spliterator(), false)
                .filter(purchase -> purchase.getCatId().equals(id))
                .findAny()
                .ifPresent(purchase -> {
                    throw new PetShopException("The cat is purchased, can't delete");
                });

         */

        catsRepository.findById(id)
                .ifPresentOrElse((cat) -> catsRepository.deleteById(cat.getId()),
                        () -> {
                            throw new PetShopException("Cat does not exist");
                        }
                );

//        catsRepository.deleteById(id).orElseThrow(() -> new PetShopException("Cat does not exist"));
        logger.trace("deleteCat - method finished");
    }

    @Override
    @Transactional
    public void updateCat(Long id, String name, String breed, Integer catYears) {
        logger.trace("updateCat - method entered - id: " + id + ", name: " + name + ", breed: " + breed +
                ", catYears: " + catYears);

        catsRepository.findById(id)
                .ifPresentOrElse((cat) -> {
                            cat.setName(name);
                            cat.setBreed(breed);
                            cat.setCatYears(catYears);
                        },
                        () -> {
                            throw new PetShopException("Cat does not exist");
                        }
                );
        logger.trace("updateCat - method finished");
    }
}