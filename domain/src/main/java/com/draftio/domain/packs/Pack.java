package com.draftio.domain.packs;

import com.draftio.domain.cards.Card;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class Pack {
    private final Set<Card> rares;
    private final Set<Card> uncommons;
    private final Set<Card> commons;

    @JsonCreator
    public static Pack createPack(
            @JsonProperty("rares") Set<Card> rares,
            @JsonProperty("uncommons") Set<Card> uncommons,
            @JsonProperty("commons") Set<Card> commons
    ) {
        return new Pack(rares, uncommons, commons);
    }
}
