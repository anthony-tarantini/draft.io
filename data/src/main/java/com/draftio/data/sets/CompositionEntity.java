package com.draftio.data.sets;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "pack_composition")
public class CompositionEntity {
    @Id
    @SequenceGenerator(name = "pack_composition_id_seq", sequenceName = "pack_composition_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pack_composition_id_seq")
    private Long id;

    private Integer commons;
    private Integer uncommons;
    private Integer rares;
    private Integer lands;

    @OneToMany(mappedBy = "composition")
    private Set<SetEntity> sets;
}
