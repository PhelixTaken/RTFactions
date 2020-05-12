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

import me.phelix.rtfactions.utils.permission.FactionPermission;

import java.util.HashSet;
import java.util.Set;

public final class Faction {

    private final String name;
    private final Set<FPlayer> players = new HashSet<>();
    private final Set<Faction> allies = new HashSet<>();
    private final Set<FLocation> claimedChunks = new HashSet<>();
    private String description;
    private FactionPermission factionPermission;

    public Faction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<FPlayer> getPlayers() {
        return players;
    }

    public Set<Faction> getAllies() {
        return allies;
    }

    public void addPlayer(FPlayer fPlayer) {
        players.add((FPlayer) fPlayer);
    }

    public void removePlayer(FPlayer fPlayer) {
        players.remove(fPlayer);
    }

    public void addAlly(Faction faction) {
        allies.add(faction);
    }

    public void removeAlly(Faction faction) {
        allies.remove(faction);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addClaim(FLocation fLocation) {
        claimedChunks.add(fLocation);
    }

    public void removeClaim(FLocation fLocation) {
        claimedChunks.remove(fLocation);
    }

    public Set<FLocation> getClaims() {
        return claimedChunks;
    }

    public FactionPermission getPermissions(){
        return factionPermission;
    }

    public void setPermission(FactionPermission factionPermission){
        this.factionPermission = factionPermission;
    }

}
