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

import me.phelix.rtfactions.utils.Role;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class FPlayer {

    private final String id;
    private transient Faction faction;
    private Role role;

    public FPlayer(String id) {
        this.id = id;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public UUID getUUID() {
        return UUID.fromString(id);
    }

    @NotNull
    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }

    @NotNull
    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        if (this.faction != null && this.faction.equals(faction))
            return;
        this.faction = faction;
    }


    public boolean hasFaction() {
        return !(faction.getName().equalsIgnoreCase("wilderness") || faction.getName().equalsIgnoreCase("warzone") || faction.getName().equalsIgnoreCase("safezone"));
    }

    public void sendMessage(String message){
        Bukkit.getPlayer(UUID.fromString(id)).sendMessage(message);
    }

    @Nullable
    public Player getPlayer(){
        return Bukkit.getPlayer(UUID.fromString(id));
    }

    public String getName(){
       return Bukkit.getOfflinePlayer(UUID.fromString(id)).getName();
    }
}
