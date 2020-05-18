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
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdPromote extends SubCommand {

    public CmdPromote(){
        super(new String[]{"promote"}, new String[]{"<player>"}, "Promote a player in your faction", Permission.PROMOTE, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 1){

            final FPlayer fPlayer = playerHandler.getByName(args[0]);

            if(fPlayer == null) {
                sendMessage(Message.commandInfoPlayerNotExist, args[0]);
                return;
            }

            if(!fPlayer.getFaction().equals(myFaction)) {
                sendMessage(Message.commandPromoteNotSameFaction);
                return;
            }

            if(fPlayer.getRole().getValue() + 1 > fme.getRole().getValue()) {
                sendMessage(Message.commandPromoteNotHigher);
                return;
            }

            final Role role = fPlayer.getRole().getByValue(fPlayer.getRole().getValue() + 1);
            fPlayer.setRole(role);
            sendMessage(Message.commandPromoteSuccessful, fPlayer.getName(), role.getPrefix());
            sendMessage(fPlayer, Message.commandPromotePlayer, role.getPrefix(), fme.getName());

        } else {
            sendMessage(toString());
        }
    }

}
