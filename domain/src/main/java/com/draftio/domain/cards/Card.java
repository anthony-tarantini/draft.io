package com.draftio.domain.cards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Card {
    private final Integer id;
    private final String name;
    private final String rarity;

    @JsonCreator
    public static Card createCard(
            @JsonProperty("id") final Integer id,
            @JsonProperty("name") final String name,
            @JsonProperty("rarity") final String rarity
    ) {
        return new Card(id, name, rarity);
    }
}
