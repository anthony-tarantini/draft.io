package com.draftio.data.sets;

import com.draftio.data.packs.PackEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="set")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class SetEntity {
    @Id
    @SequenceGenerator(name = "set_id_seq", sequenceName = "set_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="set_id_seq")
    @JsonIgnore
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "composition_id")
    @JsonIgnore
    private CompositionEntity composition;

    @OneToMany(mappedBy = "set")
    @JsonIgnore
    private Set<PackEntity> packs;
}
