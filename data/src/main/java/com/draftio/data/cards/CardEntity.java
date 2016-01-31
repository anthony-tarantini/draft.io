package com.draftio.data.cards;

import com.draftio.data.sets.SetEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CardEntity {
    @Id
    @SequenceGenerator(name = "card_id_seq", sequenceName = "card_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="card_id_seq")
    @JsonIgnore
    private Long id;

    @NonNull
    @JsonProperty("id")
    private Integer multiverseId;

    @NonNull
    private String name;

    @NonNull
    private String rarity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "set_id")
    @JsonIgnore
    private SetEntity set;
}
