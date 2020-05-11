package me.phelix.rtfactions.implementations;

import me.phelix.rtfactions.interfaces.FPlayer;
import me.phelix.rtfactions.interfaces.Faction;
import me.phelix.rtfactions.utils.Role;

import java.util.UUID;

public final class MemoryFPlayer implements FPlayer {

    private final String id;
    private Faction faction;
    private Role role;

    public MemoryFPlayer(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public UUID getUUID() {
        return UUID.fromString(id);
    }

    @Override
    public Role getRole() {
        if (role == null)
            return Role.NONE;
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Faction getFaction() {
        return faction;
    }

    @Override
    public void setFaction(Faction faction) {
        if (this.faction != null && this.faction.equals(faction))
            return;
        this.faction = faction;
    }

    @Override
    public boolean hasFaction() {
        return !(faction.getName().equalsIgnoreCase("wilderness") || faction.getName().equalsIgnoreCase("warzone") || faction.getName().equalsIgnoreCase("safezone"));
    }
}
