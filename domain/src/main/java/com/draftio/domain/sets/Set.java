package com.draftio.domain.sets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Set {
    private final String name;
    private final String code;

    @JsonCreator
    public static Set createSet(
            @JsonProperty("name") String name,
            @JsonProperty("code") String code
    ) {
        return new Set(name, code);
    }
}
