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
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdDefaultRole extends SubCommand {

    public CmdDefaultRole(){
        super(new String[]{"defaultrole", "dfr", "defaultr"}, new String[]{"<role>"}, "Set the default faction role", Permission.DEFAULT_ROLE, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 1){
            try {
                final Role role = Role.valueOf(args[0].toUpperCase());
                if(role == Role.LEADER){
                    sendMessage(Message.commandDRoleLeader);
                    return;
                }
                myFaction.setDefaultRole(role);
                sendMessage(Message.commandDRoleSet, role.toString().toLowerCase());
            } catch (IllegalArgumentException e){
                sendMessage(Message.commandDRoleNotExist, args[0]);
            }
        } else {
            fme.sendMessage(toString());
        }
    }

}
