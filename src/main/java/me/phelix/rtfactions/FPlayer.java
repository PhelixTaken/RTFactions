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

import java.util.UUID;

public final class FPlayer {

    private final String id;
    private transient Faction faction;
    private Role role;

    public FPlayer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public UUID getUUID() {
        return UUID.fromString(id);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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
}
