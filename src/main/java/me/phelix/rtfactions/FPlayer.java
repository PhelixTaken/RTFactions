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

import me.phelix.rtfactions.utils.Config;
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
    private byte chat = 0;

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
    public Player getPlayer() {
        return Bukkit.getPlayer(UUID.fromString(id));
    }

    @Nullable
    public String getName() {
        return Bukkit.getOfflinePlayer(UUID.fromString(id)).getName();
    }

    public byte getChat() {
        return chat;
    }

    public byte getChat(char c) {
        switch (c) {
            case 'p':
                chat = 0;
                return chat;
            case 'f':
                chat = 1;
                return chat;
            case 'a':
                chat = 2;
                return chat;
            default:
                chat = 0;
                return chat;
        }
    }

    public String getPrefixCharacters(){
        if(hasFaction()) {
            switch(role) {
                case MODERATOR:
                    return Config.factionModeratorPrefix;
                case COLEADER:
                    return Config.factionCoLeaderPrefix;
                case LEADER:
                    return Config.factionLeaderPrefix;
            }
        }
        return "";
    }
}
