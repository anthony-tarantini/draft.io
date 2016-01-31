package com.draftio.data.sets;

import com.draftio.domain.sets.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetsService {
    private final SetsRepository repository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SetsService(final SetsRepository repository, final ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public List<Set> fetchAllSets() {
        return repository.findAll().stream()
                .map(setEntity -> objectMapper.convertValue(setEntity, Set.class))
                .collect(Collectors.toList());
    }
}
