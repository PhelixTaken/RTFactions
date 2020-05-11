package me.phelix.rtfactions.interfaces;

import me.phelix.rtfactions.implementations.MemoryFaction;

import java.util.Map;

public interface FactionHandler {

    public Map<String, MemoryFaction> getFactionMap();

    public MemoryFaction getByName(String name);

}
