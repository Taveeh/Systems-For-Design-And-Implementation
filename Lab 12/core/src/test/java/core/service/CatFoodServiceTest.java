package core.service;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import core.ITConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("/dbtest/db-data.xml")
public class CatFoodServiceTest {
    @Autowired
    private CatFoodService catFoodService;

    @Test
    public void getCatFoodFromRepository() {
        assertEquals("there should be no cat food", 0, catFoodService.getCatFoodFromRepository().size());
    }

    @Test
    public void addCatFood() {
        catFoodService.addCatFood(1L, 1L);
        assertEquals("there should be 1 cat food", 1, catFoodService.getCatFoodFromRepository().size());
        catFoodService.deleteCatFood(1L, 1L);
    }

    @Test
    public void deleteCatFood() {
        catFoodService.addCatFood(1L, 1L);
        catFoodService.deleteCatFood(1L, 1L);
        assertEquals("there should be no cat food", 0, catFoodService.getCatFoodFromRepository().size());
    }

    @Test
    public void filterCatsThatEatCertainFood() {
        catFoodService.addCatFood(1L, 1L);
        catFoodService.addCatFood(2L, 2L);
        assertEquals("there should be 1 cat food", 1, catFoodService.filterCatsThatEatCertainFood(1L).size());
        catFoodService.deleteCatFood(1L, 1L);
        catFoodService.deleteCatFood(2L, 2L);
    }
}
