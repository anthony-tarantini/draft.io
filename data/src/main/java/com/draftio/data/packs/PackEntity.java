package com.draftio.data.packs;

import com.draftio.data.cards.CardEntity;
import com.draftio.data.sets.SetEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "created_pack")
public class PackEntity {
    @Id
    @SequenceGenerator(name = "created_pack_id_seq", sequenceName = "created_pack_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="created_pack_id_seq")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "rares",
            joinColumns = {@JoinColumn(name="pack_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="card_id", referencedColumnName = "id")}
    )
    private Set<CardEntity> rares;

    @ManyToMany
    @JoinTable(
            name = "uncommons",
            joinColumns = {@JoinColumn(name="pack_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="card_id", referencedColumnName = "id")}
    )
    private Set<CardEntity> uncommons;

    @ManyToMany
    @JoinTable(
            name = "commons",
            joinColumns = {@JoinColumn(name="pack_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="card_id", referencedColumnName = "id")}
    )
    private Set<CardEntity> commons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="set_id")
    private SetEntity set;
}
