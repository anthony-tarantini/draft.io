package com.draftio.data.packs;

import com.draftio.domain.errors.ApiError;
import com.draftio.domain.packs.Pack;
import com.draftio.domain.packs.PacksResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class PacksControllerTest {
    private PacksService mockService;
    private PacksController controller;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockService = mock(PacksService.class);
        controller = new PacksController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void onSuccessfulCallForPacksForASet_returnsPacksResponse() throws Exception {
        final List<Pack> expectedPacks = singletonList(new Pack(new HashSet<>(), new HashSet<>(), new HashSet<>()));
        doReturn(expectedPacks).when(mockService).getPacksForSet(anyString());

        final MvcResult result = mockMvc.perform(get("/packs/TST")).andReturn();
        final PacksResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PacksResponse.class);

        verify(mockService).getPacksForSet("TST");
        assertThat(response.getPacks()).isEqualTo(expectedPacks);
        assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void onUnsuccessfulCallForPacksForASet_returnsApiError() throws Exception {
        doThrow(new RuntimeException("test message")).when(mockService).getPacksForSet(anyString());

        final MvcResult result = mockMvc.perform(get("/packs/TST")).andReturn();
        final ApiError response = objectMapper.readValue(result.getResponse().getContentAsString(), ApiError.class);

        assertThat(response.getMessage()).isEqualTo("test message");
        assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}