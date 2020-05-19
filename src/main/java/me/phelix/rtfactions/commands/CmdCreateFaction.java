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

package me.phelix.rtfactions.commands;

import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.utils.Config;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.FactionPermission;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdCreateFaction extends SubCommand {

    public CmdCreateFaction() {
        super(new String[]{"create"}, new String[]{"<name>"}, "Creates a faction", Permission.NONE, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (fme.hasFaction()) {
            sendMessage(Message.commandCreateAlreadyInFaction);
            return;
        }

        if (args.length != 1) {
            sendMessage(toString());
            return;
        }

        if (args[0].length() < Config.factionNameMinLength || args[0].length() > Config.factionNameMaxLength) {
            sendMessage(Message.commandCreateNameLength, Config.factionNameMinLength, Config.factionNameMaxLength);
            return;
        }

        if (plugin.getFactionHandler().getFactionMap().containsKey(args[0])) {
            sendMessage(Message.commandCreateFactionAlreadyExists, args[0]);
            return;
        }

        plugin.getFactionHandler().getWilderness().removePlayer(fme);
        final Faction faction = new Faction(args[0]);
        plugin.getFactionHandler().getFactionMap().put(faction.getName(), faction);
        fme.setFaction(faction);
        fme.setRole(Role.LEADER);
        faction.setPower(Config.factionPowerPerPlayer);
        faction.setDefaultRole(Role.RECRUIT);
        faction.addPlayer(fme);
        faction.setDescription("Default description");
        final FactionPermission factionPermission = new FactionPermission();
        factionPermission.setDefaultPermissions();
        faction.setPermission(factionPermission);
        sendMessage(Message.commandCreateFaction, faction.getName());
    }
}

