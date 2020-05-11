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

import com.google.common.collect.*;
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

    public final Map<FLocation, Faction> chunkMap = new HashMap<>();

    private final RTFactions main;

    public MemoryChunkHandler(RTFactions main) {
        this.main = main;
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
    public void claimChunk(Chunk chunk) {

    }

    @Override
    public void claimChunk(Location location) {

    }

    @Override
    public void claimChunk(FLocation fLocation) {

    }

    @Override
    public Faction getFactionFromChunk(Chunk chunk) {
        return null;
    }

    @Override
    public Faction getFactionFromChunk(Location location) {
        return null;


    }

    @Override
    public Faction getFactionFromChunk(FLocation fLocation) {
        return chunkMap.getOrDefault(fLocation, main.getFactionHandler().getWilderness());
    }

    @Override
    public Collection<FLocation> getClaimedChunks(Faction faction) {
        return null;
    }
}
