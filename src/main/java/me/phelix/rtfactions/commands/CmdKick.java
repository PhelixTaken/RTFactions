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
import me.phelix.rtfactions.utils.Config;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdKick extends SubCommand {

    public CmdKick(){
        super(new String[]{"kick"}, new String[]{"<player>"}, "Kick a player from your faction", Permission.KICK, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 1) {
            final FPlayer fPlayer = playerHandler.getByName(args[0]);

            if (fPlayer == null) {
                sendMessage(Message.commandInfoPlayerNotExist, args[0]);
                return;
            }

            if(!fPlayer.getFaction().equals(myFaction)) {
                sendMessage(Message.commandKickNotSameFaction);
                return;
            }

            if(fPlayer.getRole().getValue() >= fme.getRole().getValue()) {
                sendMessage(Message.commandKickHigherRole);
                return;
            }

            myFaction.removePlayer(fPlayer);
            fPlayer.setFaction(factionHandler.getWilderness());

            factionHandler.getWilderness().addPlayer(fPlayer);
            fPlayer.setRole(Role.NONE);

            if(chunkHandler.getClaimedChunks(myFaction).size() > 0) {
                // 10 5 chunks per player
                int i = 0;
                while(myFaction.getPowerLeft() > myFaction.getPlayers().size() * (Config.factionPowerPerPlayer / Config.factionClaimPower) ) {
                    i++;
                    chunkHandler.unclaimAmountChunks(myFaction, i);
                }

            }

            sendMessage(Message.commandKickSuccessful, fPlayer.getName());
            if(fPlayer.getPlayer() != null) {
                sendMessage(fPlayer, Message.commandKickPlayer, myFaction.getName(), fme.getName());
            }
            myFaction.broadCast(color(String.format(Message.commandKickBroadcast, fPlayer.getName(), fme.getName())));

        } else {
            sendMessage(toString());
        }
    }
}
