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
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdAddAlly extends SubCommand {

    public CmdAddAlly(){
        super(new String[]{"ally", "addally"}, new String[]{"<faction>"}, "Ally another faction", Permission.ADD_ALLY, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){

        if(args.length == 1) {

            final Faction ally = factionHandler.getByName(args[0]);
            if(ally == null) {
                sendMessage(Message.commandInfoFactionNotExist, args[0]);
                return;
            }

            if(myFaction.equals(ally)){
                sendMessage(Message.commandAllySelf);
                return;
            }

            if(myFaction.getAllies(factionHandler).contains(ally)){
                sendMessage(Message.commandAllyAlready, args[0]);
                return;
            }

            myFaction.addAllyRequest(ally);
            sendMessage(Message.commandAllySent, ally.getName());
            final JsonMessage message = new JsonMessage();
            message.append(color(String.format(Message.commandAllyRequest, myFaction.getName())))
                    .setClickAsExecuteCmd("/f ally " + myFaction.getName() + " true").save();


            for(final FPlayer fPlayer : ally.getPlayersByPermission(Permission.ADD_ALLY)){
                if(fPlayer.getPlayer() == null) continue;
                message.send(fPlayer.getPlayer());
            }

        } else if(args.length == 2){

            if(args[1].equalsIgnoreCase("true")){

                final Faction faction = factionHandler.getByName(args[0]);

                if(faction == null){
                    sendMessage(Message.commandInfoFactionNotExist, args[0]);
                    return;
                }

                if(faction.getAllyRequests().contains(myFaction)){
                    myFaction.addAlly(faction);
                    faction.addAlly(myFaction);

                    myFaction.broadCast(color(String.format(Message.commandAllySuccess, faction.getName())));
                    faction.broadCast(color(String.format(Message.commandAllySuccess, myFaction.getName())));
                    faction.removeAllyRequest(myFaction);
                }

            }

        } else {
            sendMessage(toString());
        }
    }

}

