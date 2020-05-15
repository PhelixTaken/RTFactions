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

package me.phelix.rtfactions.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public final class Warp {

    private final String name;
    private final String password;
    private final double x;
    private final double y;
    private final double z;
    private final String worldUUID;

    public Warp(String name, String password, String worldUUID, double x, double y, double z) {
        this.name = name;
        this.password = password;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldUUID = worldUUID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Location getLocation(){
        return new Location(Bukkit.getWorld(UUID.fromString(worldUUID)), x, y, z);
    }

    public boolean hasPassword(){
        return password != null;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }




}
