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
import me.phelix.rtfactions.utils.Config;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdJoin extends SubCommand {

    public CmdJoin(){
        super(new String[]{"join"}, new String[]{"<faction>"}, "Join a faction", Permission.NONE, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 1){

            if(fme.hasFaction()){
                sendMessage(Message.commandPlayerSelfAlreadyInFaction);
                return;
            }

            final Faction faction = factionHandler.getByName(args[0]);
            if(faction.getInvitations().contains(fme)) {
                join(fme, faction);
                return;
            }

            if(faction.isOpen()){
                join(fme, faction);
            } else {
                sendMessage(Message.commandJoinFactionClosed, faction.getName());
            }

        } else {
            sendMessage(toString());
        }
    }

    private void join(FPlayer fPlayer, Faction faction){
        fPlayer.getFaction().removePlayer(fPlayer);
        fPlayer.setFaction(faction);
        fPlayer.setRole(faction.getDefaultRole());
        faction.addPower(Config.factionPowerPerPlayer);
        faction.addPlayer(fPlayer);
        faction.deinvite(fPlayer);
        sendMessage(Message.commandJoinFactionPlayer, faction.getName());
        faction.broadCast((color(String.format(Message.commandJoinFactionBroadcast, fPlayer.getPlayer().getName()))));
    }


}
