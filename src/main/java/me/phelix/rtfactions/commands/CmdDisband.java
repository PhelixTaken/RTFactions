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

import me.phelix.rtfactions.FPlayer;
import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.utils.JsonMessage;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdDisband extends SubCommand {

    public CmdDisband(){
        super(new String[]{"disband"}, new String[]{""}, "Disabnd the faction", Permission.DISBAND, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 0) {

            final JsonMessage message = new JsonMessage();

            message.append(color(Message.commandDisbandWarning)).setClickAsExecuteCmd("/f disband true").save();

            message.send(fme.getPlayer());
        } else if (args.length == 1 && args[0].equalsIgnoreCase("true")) {

            sendMessage(Message.commandDisbandSuccess, myFaction.getName());

            myFaction.getPlayers().forEach(fPlayer -> {
                sendMessage(fPlayer, Message.commandDisbandBroadcast, fme.getName());
                fPlayer.setFaction(factionHandler.getWilderness());
                fPlayer.setRole(Role.NONE);
            });

            myFaction.getAllies(factionHandler).forEach(faction -> faction.removeAlly(myFaction));

            myFaction.getPlayers().clear();
            chunkHandler.removeAllChunks(myFaction);
            factionHandler.getFactionMap().remove(myFaction.getName());

        } else {
            sendMessage(toString());
        }

    }

}
