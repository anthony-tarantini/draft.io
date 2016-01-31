package com.draftio.data.packs;

import com.draftio.data.cards.CardEntity;
import com.draftio.data.sets.SetEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "created_pack")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class PackEntity {
    @Id
    @SequenceGenerator(name = "created_pack_id_seq", sequenceName = "created_pack_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="created_pack_id_seq")
    @JsonIgnore
    private Long id;

    @NonNull
    @ManyToMany
    @JoinTable(
            name = "pack_card",
            joinColumns = {@JoinColumn(name="pack_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="card_id", referencedColumnName = "id")}
    )
    private Set<CardEntity> rares;

    @NonNull
    @ManyToMany
    @JoinTable(
            name = "pack_card",
            joinColumns = {@JoinColumn(name="pack_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="card_id", referencedColumnName = "id")}
    )
    private Set<CardEntity> uncommons;

    @ManyToMany
    @JoinTable(
            name = "pack_card",
            joinColumns = {@JoinColumn(name="pack_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="card_id", referencedColumnName = "id")}
    )
    @NonNull
    @JsonProperty("commons")
    private Set<CardEntity> commons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="set_code")
    @JsonIgnore
    private SetEntity set;
}
