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

import me.phelix.rtfactions.FPlayer;
import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.RTFactions;
import me.phelix.rtfactions.handlers.ChunkHandler;
import me.phelix.rtfactions.handlers.FactionHandler;
import me.phelix.rtfactions.handlers.PlayerHandler;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    private final String[] aliases;
    private final String[] args;
    private final String description;
    private final Permission permission;
    private final boolean factionNeeded;

    public FPlayer fme;
    public Faction myFaction;
    public RTFactions plugin;
    public PlayerHandler playerHandler;
    public FactionHandler factionHandler;
    public ChunkHandler chunkHandler;

    protected SubCommand(String[] aliases, String[] args, String description, Permission permission, boolean factionNeeded) {
        this.aliases = aliases;
        this.args = args;
        this.description = description;
        this.permission = permission;
        this.factionNeeded = factionNeeded;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public final boolean setCommandSender(CommandSender sender, RTFactions plugin, PlayerHandler playerHandler, FactionHandler factionHandler, ChunkHandler chunkHandler) {
        this.plugin = plugin;
        this.playerHandler = playerHandler;
        this.factionHandler = factionHandler;
        this.chunkHandler = chunkHandler;
        if (sender instanceof Player) {
            this.fme = playerHandler.getByPlayer((Player) sender);
            if (fme.hasFaction())
                this.myFaction = fme.getFaction();
            return true;
        } else {
            this.myFaction = null;
            this.fme = null;
        }
        return true;
    }

    public final boolean hasPermission() {

        if (fme.getRole() == null)
            return false;

        if (factionNeeded && !fme.hasFaction()) {
            sendMessage(Message.commandFactionNeeded);
            return false;
        }

        if (!factionNeeded && permission == Permission.NONE)
            return true;

        if (!myFaction.getPermissions().hasPermission(fme.getRole(), permission)) {
            sendMessage(Message.commandPermissionNeeded, permission.name().toLowerCase());
            return false;
        }

        return myFaction.getPermissions().hasPermission(fme.getRole(), permission);
    }

    public String[] getAliases() {
        return aliases;
    }

    public String[] getArgs() {
        return args;
    }

    public String getDescription() {
        return description;
    }

    public Permission getPermission() {
        return permission;
    }

    public boolean isFactionNeeded() {
        return factionNeeded;
    }

    public final void sendMessage(String message, Object... args){
        fme.sendMessage(String.format(color(message), args));
    }

    public final void sendMessage(FPlayer fPlayer, String message, Object... args){
        fPlayer.sendMessage(String.format(color(message), args));
    }

    public final void sendMessage(String message){
        fme.sendMessage(color(message));
    }

    public final void sendMessage(FPlayer fPlayer, String message){
        fPlayer.sendMessage(color(message));
    }

    public final String color(String msg){
        return ChatColor.translateAlternateColorCodes('&', Message.prefix + " " + msg);
    }

    @Override
    public final String toString() {
        return String.format("/f %s %s", String.join(" | ", aliases), String.join(" ", args));
    }

}
