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

package me.phelix.rtfactions.interfaces;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Collection;

public interface ChunkHandler {

    public boolean isClaimed(Chunk chunk);

    public boolean isClaimed(Location location);

    public boolean isClaimed(FLocation fLocation);

    public void claimChunk(Chunk chunk, Faction faction);

    public void claimChunk(Location location, Faction faction);

    public void claimChunk(FLocation fLocation, Faction faction);

    public Faction getFactionFromChunk(Chunk chunk);

    public Faction getFactionFromChunk(Location location);

    public Faction getFactionFromChunk(FLocation fLocation);

    public Collection<FLocation> getClaimedChunks(Faction faction);

}
