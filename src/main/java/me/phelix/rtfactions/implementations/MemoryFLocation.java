package me.phelix.rtfactions.implementations;

import me.phelix.rtfactions.interfaces.FLocation;
import org.bukkit.Chunk;
import org.bukkit.World;

public final class MemoryFLocation implements FLocation {

    
    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public Chunk getChunk() {
        return null;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public String getWorldName() {
        return null;
    }
}
