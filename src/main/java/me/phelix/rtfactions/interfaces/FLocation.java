package me.phelix.rtfactions.interfaces;

import org.bukkit.Chunk;
import org.bukkit.World;

public interface FLocation {

    public int getX();

    public int getZ();

    public Chunk getChunk();

    public World getWorld();

    public String getWorldName();

}
