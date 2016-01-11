package com.draftio.gateway.sets;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SetsRepositoryTest {

    @Test
    public void allAvailableSets_returnsAllSets() {
        SetsRepository repository = new SetsRepository();

        List<String> sets = repository.allAvailableSets();

        assertThat(sets).containsExactly(
                "Innistrad",
                "Dark Ascension",
                "Avacyn Restored",
                "M13",
                "Return to Ravnica",
                "Gatecrash",
                "Dragon's Maze",
                "M14",
                "Theros",
                "Born of the Gods",
                "Journey into Nyx",
                "M15",
                "Khans of Tarkir",
                "Fate Reforged",
                "Dragons of Tarkir",
                "Magic: Origins",
                "Battle for Zendikar"
        );
    }
}