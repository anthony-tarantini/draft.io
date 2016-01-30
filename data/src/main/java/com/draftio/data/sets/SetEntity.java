package com.draftio.data.sets;

import javax.persistence.*;

@Entity
@Table(name="set")
public class SetEntity {
    @Id
    @SequenceGenerator(name = "set_id_seq", sequenceName = "set_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="set_id_seq")
    private Long id;

    private String name;
    private String code;

    @ManyToMany
}
