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
import me.phelix.rtfactions.utils.JsonMessage;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CmdInvite extends SubCommand {

    public CmdInvite(){
        super(new String[]{"invite"}, new String[]{"<player>"}, "Invites a player to the faction", Permission.INVITING, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 1){
            final String name = args[0];
            final Player player = Bukkit.getPlayer(name);
            if(player == null){
                sendMessage(Message.commandInvitePlayerNotExist, name);
                return;
            }

            final FPlayer target = playerHandler.getByPlayer(player);

            if(myFaction.getPlayers().contains(target)) {
                sendMessage(Message.commandInvitePlayerAlreadyInSameFaction, name);
                return;
            }

            if(target.hasFaction() && !myFaction.getPlayers().contains(target)){
                sendMessage(Message.commandPlayerAlreadyInFaction, name);
                return;
            }

            sendMessage(Message.commandInviteSuccessfullyInvited, name);
            final JsonMessage message = new JsonMessage();
            message.append(color(String.format(Message.commandInviteSuccessfullyInvitedPlayer, fme.getPlayer().getName(), myFaction.getName()))).setClickAsExecuteCmd("/f join " + myFaction.getName()).save();
            message.send(target.getPlayer());
            myFaction.invite(target);

        } else {
            sendMessage(toString());
        }
    }

}
