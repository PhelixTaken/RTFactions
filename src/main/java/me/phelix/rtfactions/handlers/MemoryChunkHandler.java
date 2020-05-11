package me.phelix.rtfactions.handlers;

import me.phelix.rtfactions.interfaces.ChunkHandler;
import me.phelix.rtfactions.interfaces.FLocation;
import me.phelix.rtfactions.interfaces.Faction;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Collection;

public final class MemoryChunkHandler implements ChunkHandler {



    @Override
    public boolean isClaimed(Chunk chunk) {
        return false;
    }

    @Override
    public boolean isClaimed(Location location) {
        return false;
    }

    @Override
    public boolean isClaimed(FLocation fLocation) {
        return false;
    }

    @Override
    public void claimChunk(Chunk chunk) {

    }

    @Override
    public void claimChunk(Location location) {

    }

    @Override
    public void claimChunk(FLocation fLocation) {

    }

    @Override
    public Collection<FLocation> getClaimedChunks(Faction faction) {
        return null;
    }
}
