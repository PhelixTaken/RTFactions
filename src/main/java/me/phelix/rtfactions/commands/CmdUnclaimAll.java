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

import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdUnclaimAll extends SubCommand {

    public CmdUnclaimAll(){
        super(new String[]{"unclaimall", "unclaima", "uca"}, new String[]{""}, "Unclaim all the claimed chunks from your faction", Permission.UNCLAIMING_ALL, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 0) {

            if(myFaction.getClaims().isEmpty()) {
                sendMessage(Message.commandUnclaimAllNone);
                return;
            }

            chunkHandler.removeAllChunks(myFaction);

            sendMessage(Message.commandUnclaimAllSuccessful);

        } else {
            sendMessage(toString());
        }
    }

}
