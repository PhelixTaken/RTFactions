package me.phelix.rtfactions.implementations;

import me.phelix.rtfactions.interfaces.FPlayer;
import me.phelix.rtfactions.interfaces.Faction;

import java.util.HashSet;
import java.util.Set;

public final class MemoryFaction implements Faction {

    private final String name;
    private final Set<FPlayer> players = new HashSet<>();
    private final Set<me.phelix.rtfactions.interfaces.Faction> allies = new HashSet<>();
    private String description;

    public MemoryFaction(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<FPlayer> getPlayers() {
        return players;
    }

    @Override
    public Set<me.phelix.rtfactions.interfaces.Faction> getAllies() {
        return allies;
    }

    @Override
    public void addPlayer(FPlayer fPlayer) {
        players.add(fPlayer);
    }

    @Override
    public void removePlayer(FPlayer fPlayer) {
        players.remove(fPlayer);
    }

    @Override
    public void addAlly(me.phelix.rtfactions.interfaces.Faction faction) {
        allies.add(faction);
    }

    @Override
    public void removeAlly(me.phelix.rtfactions.interfaces.Faction faction) {
        allies.remove(faction);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
