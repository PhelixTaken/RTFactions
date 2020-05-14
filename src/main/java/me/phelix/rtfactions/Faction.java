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

import me.phelix.rtfactions.handlers.FactionHandler;
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.permission.FactionPermission;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class Faction {

    private final String name;
    private final Set<FPlayer> players = new HashSet<>();
    private final Set<String> allyNames = new HashSet<>();
    private transient Set<FLocation> claimedChunks = new HashSet<>();
    private final transient Set<FPlayer> invitations = new HashSet<>();
    private final transient Set<Faction> allyRequests = new HashSet<>();
    private String description;
    private FactionPermission factionPermission;
    private int power;
    private Role defaultRole;
    private boolean open = false;

    public Faction(String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public Set<FPlayer> getPlayers() {
        return players;
    }

    @NotNull
    public Set<String> getAllyNames(){
        return allyNames;
    }

    public Set<Faction> getAllies(FactionHandler factionHandler){
        final Set<Faction> factions = new HashSet<>();
        for(String string : getAllyNames()) {
            factions.add(factionHandler.getByName(string));
        }
        return factions;
    }

    public void addPlayer(FPlayer fPlayer) {
        players.add((FPlayer) fPlayer);
    }

    public void removePlayer(FPlayer fPlayer) {
        players.remove(fPlayer);
    }

    public void addAlly(Faction faction) {
        allyNames.add(faction.getName());
    }

    public void removeAlly(Faction faction) {
        allyNames.remove(faction.getName());
    }

    @NotNull
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

    @NotNull
    public Set<FLocation> getClaims() {
        if(claimedChunks == null)
            claimedChunks = new HashSet<>();
        return claimedChunks;
    }

    @NotNull
    public FactionPermission getPermissions() {
        return factionPermission;
    }

    public void setPermission(FactionPermission factionPermission) {
        this.factionPermission = factionPermission;
    }

    public final int getTotalPower() {
        return power;
    }

    public final void setTotalPower(int amount) {
        power = amount;
    }

    public final int getPowerLeft() {
        return power - claimedChunks.size();
    }

    @NotNull
    public Role getDefaultRole() {
        if (defaultRole == null)
            defaultRole = Role.RECRUIT;
        return defaultRole;
    }

    public void setDefaultRole(Role role) {
        defaultRole = role;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @NotNull
    public Set<FPlayer> getInvitations(){
        return invitations;
    }

    public void invite(FPlayer fPlayer){
        invitations.add(fPlayer);
    }

    public void deinvite(FPlayer fPlayer){
        invitations.remove(fPlayer);
    }

    public final void broadCast(String message) {
        for (final FPlayer fPlayer : getPlayers()) {
            final Player player = Bukkit.getPlayer(fPlayer.getUUID());
            if (player != null) {
                player.sendMessage(message);
            }
        }
    }

    public final Set<FPlayer> getPlayersByRole(Role role) {
        return getPlayers().stream().filter(fPlayer -> fPlayer.getRole() == role).collect(Collectors.toSet());
    }

    @NotNull
    public FPlayer getLeader(){
        return getPlayersByRole(Role.LEADER).stream().findFirst().get();
    }

    @NotNull
    public Set<Faction> getAllyRequests(){
        return allyRequests;
    }

    public void addAllyRequest(Faction ally){
        allyRequests.add(ally);
    }

    public void removeAllyRequest(Faction ally){
        allyRequests.remove(ally);
    }

    @NotNull
    public Set<FPlayer> getPlayersByPermission(Permission permission){
        final Set<FPlayer> fPlayers = new HashSet<>();
        for(final FPlayer fPlayer : players) {
            if(factionPermission.hasPermission(fPlayer.getRole(), permission)){
                fPlayers.add(fPlayer);
            }
        }
        return fPlayers;
    }

}
