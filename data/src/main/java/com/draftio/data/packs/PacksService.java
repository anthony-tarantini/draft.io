package com.draftio.data.packs;

import com.draftio.domain.packs.Pack;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacksService {
  private final PacksRepository repository;
  private final ObjectMapper objectMapper;

  @Autowired
  public PacksService(final PacksRepository repository, final ObjectMapper objectMapper) {
    this.repository = repository;
    this.objectMapper = objectMapper;
  }

  public List<Pack> getPacksForSet(final String setCode) {
    return repository.findTop3BySetCode(setCode).stream()
        .map(packEntity -> objectMapper.convertValue(packEntity, Pack.class))
        .collect(Collectors.toList());
  }
}
