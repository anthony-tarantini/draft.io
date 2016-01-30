package com.draftio.data.cards;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class CardEntity {
    @Id
    @SequenceGenerator(name = "card_id_seq", sequenceName = "card_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="car_id_seq")
    private Long id;

    private String name;
    private String rarity;
}
