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

import me.phelix.rtfactions.FLocation;
import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.RTFactions;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class ChunkHandler {

    private final Map<FLocation, String> chunkMap = new HashMap<>();

    private final RTFactions main;

    public ChunkHandler(RTFactions main) {
        this.main = main;
    }

    public Map<FLocation, String> getChunkMap() {
        return chunkMap;
    }

    public boolean isClaimed(Chunk chunk) {
        return isClaimed(new FLocation(chunk));
    }

    public boolean isClaimed(Location location) {
        return isClaimed(new FLocation(location));
    }

    public boolean isClaimed(FLocation fLocation) {
        return chunkMap.containsKey(fLocation);
    }

    public void claimChunk(Chunk chunk, Faction faction) {
        final FLocation fLocation = new FLocation(chunk);
        faction.addClaim(fLocation);
        chunkMap.put(fLocation, faction.getName());
    }

    public void claimChunk(Location location, Faction faction) {
        final FLocation fLocation = new FLocation(location);
        faction.addClaim(fLocation);
        chunkMap.put(fLocation, faction.getName());
    }

    public void claimChunk(FLocation fLocation, Faction faction) {
        faction.addClaim(fLocation);
        chunkMap.put(fLocation, faction.getName());
    }

    public void unclaimChunk(Chunk chunk) {
        final FLocation fLocation = new FLocation(chunk);
        getFactionFromChunk(chunk).removeClaim(fLocation);
        chunkMap.remove(fLocation);
    }

    public void unclaimChunk(Location location) {
        final FLocation fLocation = new FLocation(location);
        getFactionFromChunk(fLocation).removeClaim(fLocation);
        chunkMap.remove(fLocation);
    }

    public void unclaimChunk(FLocation fLocation) {
        getFactionFromChunk(fLocation).removeClaim(fLocation);
        chunkMap.remove(fLocation);
    }

    public void removeAllChunks(Faction faction) {
        for (final FLocation fLocation : faction.getClaims()) {
            chunkMap.remove(fLocation);
        }
        faction.getClaims().clear();
    }

    public Faction getFactionFromChunk(Chunk chunk) {
        if (chunkMap.get(new FLocation(chunk)) == null) {
            return main.getFactionHandler().getWilderness();
        }
        return main.getFactionHandler().getByName(chunkMap.get(new FLocation(chunk)));
    }

    public Faction getFactionFromChunk(Location location) {
        if(chunkMap.get(new FLocation(location)) == null) {
            return main.getFactionHandler().getWilderness();
        }
        return main.getFactionHandler().getByName(chunkMap.get(new FLocation(location)));
    }

    public Faction getFactionFromChunk(FLocation fLocation) {
        if(chunkMap.get(fLocation) == null) {
            return main.getFactionHandler().getWilderness();
        }
        return main.getFactionHandler().getByName(chunkMap.get(fLocation));
    }

    public Collection<FLocation> getClaimedChunks(Faction faction) {
        return faction.getClaims();
    }
}
