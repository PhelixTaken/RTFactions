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

import java.util.Set;

public interface Faction {

    public String getName();

    public Set<FPlayer> getPlayers();

    public Set<Faction> getAllies();

    public void addPlayer(FPlayer fPlayer);

    public void removePlayer(FPlayer fPlayer);

    public void addAlly(Faction faction);

    public void removeAlly(Faction faction);

    public String getDescription();

    public void setDescription(String description);

    public void addClaim(FLocation fLocation);

    public void removeClaim(FLocation fLocation);

    public Set<FLocation> getClaims();

}
