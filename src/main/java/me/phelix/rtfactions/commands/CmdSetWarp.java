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
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdSetWarp extends SubCommand {

    public CmdSetWarp(){
        super(new String[]{"setwarp", "setw", "sw"}, new String[]{"<name> [password]"}, "Set a warp for the faction", Permission.SET_WARP, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0 && args.length < 3) {

            final String name = args[0];
            String password = null;
            if (args.length == 2)
                password = args[1];

            if (myFaction.warpExists(name)) {
                sendMessage(Message.commandSetWarpExists, name);
                return;
            }

            final Faction chunk = chunkHandler.getFactionFromChunk(fme.getPlayer().getLocation());
            if (!chunk.equals(myFaction) && !chunk.equals(factionHandler.getWilderness())) {
                sendMessage(Message.commandSetWarpTerritory);
                return;
            }

            if (password != null) {
                sendMessage(Message.commandSetWarpWithPassword, name, password);
                myFaction.addWarp(name, password, fme.getPlayer().getLocation());
            } else {
                sendMessage(Message.commandSetWarpWithPassword, name);
                myFaction.addWarp(name, null, fme.getPlayer().getLocation());
            }

        } else {
            sendMessage(toString());
        }
    }

}
