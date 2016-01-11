package com.draftio.gateway.sets;

import com.draftio.domain.sets.SetsResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SetsControllerTest {
    private MockMvc mockMvc;
    private SetsController setsController;
    private SetsRepository mockSetsRepo;

    @Before
    public void setup() {
        mockSetsRepo = mock(SetsRepository.class);
        setsController = new SetsController(mockSetsRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(setsController).build();
    }

    @Test
    public void whenRequestingAvailableSets_endpointBeAGet_andShouldReturnJsonOnSuccess() throws Exception {
        mockMvc.perform(get("/sets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void WhenRequestingAvailableSets_returnsAllAvailableSetsFromDatasource() {
        List<String> expectedList = emptyList();
        when(mockSetsRepo.allAvailableSets()).thenReturn(expectedList);

        SetsResponse response = setsController.getAvailableSets();

        verify(mockSetsRepo).allAvailableSets();
        assertThat(response.getSets()).isEqualTo(expectedList);
    }
    
}