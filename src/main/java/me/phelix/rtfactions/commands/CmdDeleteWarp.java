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

public final class CmdDeleteWarp extends SubCommand {

    public CmdDeleteWarp(){
        super(new String[]{"deletewarp", "delwarp", "dw", "removewarp", "removew", "rw"}, new String[]{"<warp>"}, "", Permission.DELETE_WARP, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){

        if(args.length == 1) {

            final String name = args[0];

            if(!myFaction.warpExists(name)) {
                sendMessage(Message.commandWarpNotExist, name);
                return;
            }

            myFaction.removeWarp(name);
            sendMessage(Message.commandDeleteWarpSuccess, name);

        } else {
            sendMessage(toString());
        }

    }

}
