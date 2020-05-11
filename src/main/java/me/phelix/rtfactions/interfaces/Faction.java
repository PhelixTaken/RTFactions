package me.phelix.rtfactions.interfaces;

import java.util.Set;

public interface Faction {

    public String getName();

    public Set<FPlayer> getPlayers();

    public Set<Faction> getAllies();

    public void addPlayer(FPlayer fPlayer);

    public void removePlayer(FPlayer fPlayer);

    public void addAlly(Faction faction);

    public void removeAlly(Faction faction);

    public String getDescription();

    public void setDescription(String description);

}
