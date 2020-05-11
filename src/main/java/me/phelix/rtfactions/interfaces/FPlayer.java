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

import me.phelix.rtfactions.utils.Role;

import java.util.UUID;

public interface FPlayer {

    public String getId();

    public UUID getUUID();

    public Role getRole();

    public void setRole(Role role);

    public Faction getFaction();

    public void setFaction(Faction faction);

    public boolean hasFaction();

}
