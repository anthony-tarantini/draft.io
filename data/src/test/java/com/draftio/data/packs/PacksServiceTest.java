package com.draftio.data.packs;

import com.draftio.data.TestMapper;
import com.draftio.data.cards.CardEntity;
import com.draftio.domain.cards.Card;
import com.draftio.domain.packs.Pack;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.util.collections.Sets.newSet;

public class PacksServiceTest {

  private PacksRepository mockRepository;
  private PacksService service;

  @Before
  public void setup() {
    mockRepository = mock(PacksRepository.class);
    final ObjectMapper objectMapper = TestMapper.get();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    service = new PacksService(mockRepository, objectMapper);
  }

  @Test
  public void fetchingAllSets_shouldTranslateEntitiesToDomain() {
    final List<PackEntity> entities = singletonList(new PackEntity(
        newSet(new CardEntity(12345, "cardOne", "rare")),
        newSet(new CardEntity(23456, "cardTwo", "uncommon")),
        newSet(new CardEntity(34567, "cardThree", "common"))
    ));
    doReturn(entities).when(mockRepository).findTop3BySetCode(anyString());

    List<Pack> packs = service.getPacksForSet("TST");

    verify(mockRepository).findTop3BySetCode("TST");
    assertThat(packs).containsOnly(new Pack(
            newSet(new Card(12345, "cardOne", "rare")),
            newSet(new Card(23456, "cardTwo", "uncommon")),
            newSet(new Card(34567, "cardThree", "common"))
        )
    );
  }
}