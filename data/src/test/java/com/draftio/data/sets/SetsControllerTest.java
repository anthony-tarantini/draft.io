package com.draftio.data.sets;

import com.draftio.domain.errors.ApiError;
import com.draftio.domain.sets.Set;
import com.draftio.domain.sets.SetsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class SetsControllerTest {
    private SetsService mockService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockService = mock(SetsService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new SetsController(mockService))
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void onSuccessfulCallForAllSets_returnsSetsResponse() throws Exception {
        final List<Set> expectedSets = singletonList(new Set("name", "code"));
        doReturn(expectedSets).when(mockService).fetchAllSets();

        final MvcResult result = mockMvc.perform(get("/sets")).andReturn();
        final SetsResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), SetsResponse.class);

        verify(mockService).fetchAllSets();
        assertThat(response.getSets()).isEqualTo(expectedSets);
        assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void onUnsuccessfulCallForAllSets_returnsApiError() throws Exception {
        doThrow(new RuntimeException("test message")).when(mockService).fetchAllSets();

        final MvcResult result = mockMvc.perform(get("/sets")).andReturn();
        final ApiError response = objectMapper.readValue(result.getResponse().getContentAsString(), ApiError.class);

        assertThat(response.getMessage()).isEqualTo("test message");
        assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}