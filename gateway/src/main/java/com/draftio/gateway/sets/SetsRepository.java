package com.draftio.gateway.sets;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class SetsRepository {
    private static final List<String> sets = new ArrayList<String>();

    static {
        sets.add("Innistrad");
        sets.add("Dark Ascension");
        sets.add("Avacyn Restored");
        sets.add("M13");
        sets.add("Return to Ravnica");
        sets.add("Gatecrash");
        sets.add("Dragon's Maze");
        sets.add("M14");
        sets.add("Theros");
        sets.add("Born of the Gods");
        sets.add("Journey into Nyx");
        sets.add("M15");
        sets.add("Khans of Tarkir");
        sets.add("Fate Reforged");
        sets.add("Dragons of Tarkir");
        sets.add("Magic: Origins");
        sets.add("Battle for Zendikar");
    }

    public List<String> allAvailableSets() {
        return sets ;
    }
}
