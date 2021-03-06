import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.domain.Cat;
import core.service.CatService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.controller.CatController;
import web.converter.CatConverter;
import web.dto.CatDTO;

import static org.hamcrest.CoreMatchers.anyOf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CatControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CatController catController;

    @Mock
    private CatService catService;

    @Mock
    private CatConverter catConverter;

    private Cat cat1;
    private Cat cat2;
    private CatDTO catDTO1;
    private CatDTO catDTO2;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(catController)
                .build();
        initData();
    }

    public void initData() {
        cat1 = new Cat(0L, "c1", "b1", 1);
        cat2 = new Cat(1L, "c2", "b2", 2);

        catDTO1 = createCatDTO(cat1);
        catDTO2 = createCatDTO(cat2);
    }

    public CatDTO createCatDTO(Cat cat) {
        CatDTO catDTO = CatDTO.builder()
                .name(cat.getName())
                .breed(cat.getBreed())
                .catYears(cat.getCatYears())
                .build();
        catDTO.setId(cat.getId());
        return catDTO;
    }

    @Test
    public void getCatsFromRepository() throws Exception {
        List<Cat> cats = Arrays.asList(cat1, cat2);
        Set<CatDTO> catDTOSet = new HashSet<>(Arrays.asList(catDTO1, catDTO2));
        when(catService.getCatsFromRepository()).thenReturn(cats);
        when(catConverter.convertModelsToDTOs(cats)).thenReturn(catDTOSet);
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/cats")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cats", hasSize(2)))
                .andExpect(jsonPath("$.cats[0].name", anyOf(is("c1"), is("c2"))))
                .andExpect(jsonPath("$.cats[0].breed", anyOf(is("b1"), is("b2"))));
        String result = resultActions.andReturn().getResponse().getContentAsString();

        verify(catService, times(1)).getCatsFromRepository();
        verify(catConverter, times(1)).convertModelsToDTOs(cats);
        verifyNoMoreInteractions(catService, catConverter);
    }

    private String toJsonString(CatDTO catDTO) {
        try {
            return new ObjectMapper().writeValueAsString(catDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateCat() throws Exception {
        when(catConverter.convertDtoToModel(catDTO1)).thenReturn(cat1);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/cats/{id}", cat1.getId(), catDTO1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(catDTO1)))
                .andExpect(status().isOk());

        verify(catService, times(1)).updateCat(cat1.getId(),
                catDTO1.getName(), catDTO1.getBreed(), catDTO1.getCatYears());
        verify(catConverter, times(1)).convertDtoToModel(catDTO1);
        verifyNoMoreInteractions(catService, catConverter);
    }

    @Test
    public void addCat() throws Exception {
        when(catConverter.convertDtoToModel(catDTO1)).thenReturn(cat1);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/cats", catDTO1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(catDTO1)))
                .andExpect(status().isOk());

        verify(catService, times(1)).addCat(catDTO1.getName(), catDTO1.getBreed(), catDTO1.getCatYears());
        verify(catConverter, times(1)).convertDtoToModel(catDTO1);
        verifyNoMoreInteractions(catService, catConverter);
    }

    @Test
    public void deleteCat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/cats/" + cat1.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(catService, times(1)).deleteCat(cat1.getId());
        verifyNoMoreInteractions(catService, catConverter);
    }

    @Test
    public void getCatsWithToysFromRepository() throws Exception {
        List<Cat> cats = Arrays.asList(cat1, cat2);
        Set<CatDTO> catDTOSet = new HashSet<>(Arrays.asList(catDTO1, catDTO2));
        when(catService.getCatsFromRepository()).thenReturn(cats);
        when(catConverter.convertModelsToDTOs(cats)).thenReturn(catDTOSet);
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/cats")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cats", hasSize(2)))
                .andExpect(jsonPath("$.cats[0].name", anyOf(is("c1"), is("c2"))))
                .andExpect(jsonPath("$.cats[0].breed", anyOf(is("b1"), is("b2"))));
        String result = resultActions.andReturn().getResponse().getContentAsString();

        verify(catService, times(1)).getCatsFromRepository();
        verify(catConverter, times(1)).convertModelsToDTOs(cats);
        verifyNoMoreInteractions(catService, catConverter);
    }
}
