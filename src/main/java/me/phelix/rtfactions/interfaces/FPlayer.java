package me.phelix.rtfactions.interfaces;

import me.phelix.rtfactions.utils.Role;

import java.util.UUID;

public interface FPlayer {

    public String getId();

    public UUID getUUID();

    public Role getRole();

    public void setRole(Role role);

    public Faction getFaction();

    public void setFaction(Faction faction);

    public boolean hasFaction();

}
