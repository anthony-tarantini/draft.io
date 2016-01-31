package com.draftio.data.sets;

import com.draftio.data.TestMapper;
import com.draftio.domain.sets.Set;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SetsServiceTest {

    private SetsRepository mockRepository;
    private SetsService service;

    @Before
    public void setup() {
        mockRepository = mock(SetsRepository.class);
        final ObjectMapper objectMapper = TestMapper.get();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        service = new SetsService(mockRepository, objectMapper);
    }

    @Test
    public void fetchingAllSets_shouldTranslateEntitiesToDomain() {
        final List<SetEntity> entities = singletonList(new SetEntity("Test Set", "TST"));
        doReturn(entities).when(mockRepository).findAll();

        List<Set> sets = service.fetchAllSets();

        assertThat(sets).containsOnly(new Set("Test Set", "TST"));
    }
}