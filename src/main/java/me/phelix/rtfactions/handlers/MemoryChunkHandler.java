/*
 * Copyright (C) 2020 Phelix
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.phelix.rtfactions.handlers;

import me.phelix.rtfactions.RTFactions;
import me.phelix.rtfactions.implementations.MemoryFLocation;
import me.phelix.rtfactions.interfaces.ChunkHandler;
import me.phelix.rtfactions.interfaces.FLocation;
import me.phelix.rtfactions.interfaces.Faction;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class MemoryChunkHandler implements ChunkHandler {

    private final Map<FLocation, Faction> chunkMap = new HashMap<>();

    private final RTFactions main;

    public MemoryChunkHandler(RTFactions main) {
        this.main = main;
    }

    @Override
    public Map<FLocation, Faction> getChunkMap() {
        return chunkMap;
    }

    @Override
    public boolean isClaimed(Chunk chunk) {
        return isClaimed(new MemoryFLocation(chunk));
    }

    @Override
    public boolean isClaimed(Location location) {
        return isClaimed(new MemoryFLocation(location));
    }

    @Override
    public boolean isClaimed(FLocation fLocation) {
        return chunkMap.containsKey(fLocation);
    }

    @Override
    public void claimChunk(Chunk chunk, Faction faction) {
        final FLocation fLocation = new MemoryFLocation(chunk);
        faction.addClaim(fLocation);
        chunkMap.put(fLocation, faction);
    }

    @Override
    public void claimChunk(Location location, Faction faction) {
        final FLocation fLocation = new MemoryFLocation(location);
        faction.addClaim(fLocation);
        chunkMap.put(fLocation, faction);
    }

    @Override
    public void claimChunk(FLocation fLocation, Faction faction) {
        faction.addClaim(fLocation);
        chunkMap.put(fLocation, faction);
    }

    @Override
    public void removeChunk(FLocation fLocation) {
        chunkMap.get(fLocation).getClaims().remove(fLocation);
        chunkMap.remove(fLocation);
    }

    @Override
    public void removeAllChunks(Faction faction) {
        for(final FLocation fLocation : faction.getClaims()) {
            chunkMap.remove(fLocation);
        }
        faction.getClaims().clear();
    }

    @Override
    public Faction getFactionFromChunk(Chunk chunk) {
        return chunkMap.getOrDefault(new MemoryFLocation(chunk), main.getFactionHandler().getWilderness());
    }

    @Override
    public Faction getFactionFromChunk(Location location) {
        return chunkMap.getOrDefault(new MemoryFLocation(location), main.getFactionHandler().getWilderness());
    }

    @Override
    public Faction getFactionFromChunk(FLocation fLocation) {
        return chunkMap.getOrDefault(fLocation, main.getFactionHandler().getWilderness());
    }

    @Override
    public Collection<FLocation> getClaimedChunks(Faction faction) {
        return faction.getClaims();
    }
}
