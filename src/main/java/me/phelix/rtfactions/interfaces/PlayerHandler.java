package me.phelix.rtfactions.interfaces;

import me.phelix.rtfactions.implementations.MemoryFPlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface PlayerHandler {

    public Map<UUID, MemoryFPlayer> getPlayerMap();

    public MemoryFPlayer getByID(String id);

    public MemoryFPlayer getByPlayer(Player player);

    public MemoryFPlayer getByName(String name);

}
