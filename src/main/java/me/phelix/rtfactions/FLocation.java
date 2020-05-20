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

package me.phelix.rtfactions;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.Objects;
import java.util.UUID;

public final class FLocation {

    private final int x;
    private final int z;
    private final String worldUUID;

    public FLocation(Location location) {
        x = location.getChunk().getX();
        z = location.getChunk().getZ();
        worldUUID = location.getWorld().getUID().toString();
    }

    public FLocation(String id, int x, int z) {
        this.x = x;
        this.z = z;
        worldUUID = id;
    }

    public FLocation(Chunk chunk) {
        x = chunk.getX();
        z = chunk.getZ();
        worldUUID = chunk.getWorld().getUID().toString();
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public Chunk getChunk() {
        return Bukkit.getWorld(worldUUID).getChunkAt(x, z);
    }

    public UUID getWorldUUID() {
        return UUID.fromString(worldUUID);
    }

    public String getWorldName() {
        return Bukkit.getWorld(worldUUID).getName();
    }

    public FLocation getRelative(int dx, int dz) {
        return new FLocation(worldUUID, x + dx, z + dz);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FLocation that = (FLocation) o;
        return x == that.x &&
                z == that.z &&
                worldUUID.equals(that.worldUUID);
    }

    public int hashCode() {
        return Objects.hash(x, z, worldUUID);
    }
}
