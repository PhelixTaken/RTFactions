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
import me.phelix.rtfactions.interfaces.FPlayer;
import me.phelix.rtfactions.interfaces.Faction;

import java.util.HashSet;
import java.util.Set;

public final class MemoryFaction implements Faction {

    private final String name;
    private final Set<FPlayer> players = new HashSet<>();
    private final Set<Faction> allies = new HashSet<>();
    private final Set<FLocation> claimedChunks = new HashSet<>();
    private String description;

    public MemoryFaction(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<FPlayer> getPlayers() {
        return players;
    }

    @Override
    public Set<Faction> getAllies() {
        return allies;
    }

    @Override
    public void addPlayer(FPlayer fPlayer) {
        players.add(fPlayer);
    }

    @Override
    public void removePlayer(FPlayer fPlayer) {
        players.remove(fPlayer);
    }

    @Override
    public void addAlly(Faction faction) {
        allies.add(faction);
    }

    @Override
    public void removeAlly(Faction faction) {
        allies.remove(faction);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void addClaim(FLocation fLocation) {
        claimedChunks.add(fLocation);
    }

    @Override
    public void removeClaim(FLocation fLocation) {
        claimedChunks.remove(fLocation);
    }

    @Override
    public Set<FLocation> getClaims() {
        return claimedChunks;
    }


}
