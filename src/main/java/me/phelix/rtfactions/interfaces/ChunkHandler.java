package me.phelix.rtfactions.interfaces;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Collection;

public interface ChunkHandler {

    public boolean isClaimed(Chunk chunk);

    public boolean isClaimed(Location location);

    public boolean isClaimed(FLocation fLocation);

    public void claimChunk(Chunk chunk);

    public void claimChunk(Location location);

    public void claimChunk(FLocation fLocation);

    public Collection<FLocation> getClaimedChunks(Faction faction);

}
