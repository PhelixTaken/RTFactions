package me.phelix.rtfactions.handlers;

import me.phelix.rtfactions.implementations.MemoryFaction;
import me.phelix.rtfactions.interfaces.FactionHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class MemoryFactionHandler implements FactionHandler {

    private Map<String, MemoryFaction> factionMap = new HashMap<>();

    /**
     * Holds all the factions.
     * You can get it with the faction name.
     * Holds Faction as value.
     *
     * @return factionMap
     */
    @Override
    @NotNull
    public final Map<String, MemoryFaction> getFactionMap() {
        return factionMap;
    }

    /**
     * Gets the faction with a name.
     *
     * @param name The faction name that you want to get.
     * @return Faction that exists.
     */
    @Override
    @Nullable
    public final MemoryFaction getByName(String name) {
        return factionMap.get(name);
    }
}
