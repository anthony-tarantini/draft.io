package com.draftio.domain.sets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SetsResponse {
    private final List<Set> sets;

    @JsonCreator
    public static SetsResponse createSetsResponse(
            @JsonProperty("sets") List<Set> sets
    ) {
        return new SetsResponse(sets);
    }
}
