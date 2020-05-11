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

package me.phelix.rtfactions.implementations;

import me.phelix.rtfactions.interfaces.FLocation;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public final class MemoryFLocation implements FLocation {

    private final int x;
    private final int z;
    private transient final World world;

    public MemoryFLocation(Location location){
        x = location.getChunk().getX();
        z = location.getChunk().getZ();
        world = location.getWorld();
    }

    public MemoryFLocation(Chunk chunk){
        x = chunk.getX();
        z = chunk.getZ();
        world = chunk.getWorld();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public Chunk getChunk() {
        return Bukkit.getWorld(world.getUID()).getChunkAt(x, z);
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public String getWorldName() {
        return world.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoryFLocation that = (MemoryFLocation) o;
        return x == that.x &&
                z == that.z &&
                world.equals(that.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z, world);
    }
}
