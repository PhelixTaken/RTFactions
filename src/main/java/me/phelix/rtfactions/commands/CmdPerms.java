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

import me.phelix.rtfactions.RTFactions;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public final class CmdPerms extends SubCommand implements TabCompleter {

    public CmdPerms(RTFactions rtFactions){
        super(new String[]{"setperms", "perms", "perm"}, new String[]{"<role> <permission> <true/false>"}, "Set permissions of the faction", Permission.PERMS, true);
        rtFactions.getCommand("f").setTabCompleter(this);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 5){
            try {
                final Role role = Role.valueOf(args[2]);
                final Permission permission = Permission.valueOf(args[3]);

                if(args[4].equalsIgnoreCase("true")){
                    myFaction.getPermissions().addPermission(role, permission);
                    sendMessage(Message.commandPermAdded, args[3], args[2]);
                } else if(args[4].equalsIgnoreCase("false")){
                    myFaction.getPermissions().removePermission(role, permission);
                    sendMessage(Message.commandPermRemoved, args[3], args[2]);
                } else {
                    fme.sendMessage(toString());
                }
            }catch (IllegalArgumentException e){
                sendMessage(Message.commandPermRoleNotExist, args[2], args[3]);
            }
        } else {
            fme.sendMessage(toString());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        final List<String> tab = new ArrayList<>();

        if(cmd.getName().equalsIgnoreCase("f")) {
            if (args.length > 2 && args[1].equalsIgnoreCase("role")) {
                if (args.length == 3) {
                    for (Role role : Role.values()) {
                        if (role == Role.NONE) continue;
                        tab.add(role.name());
                    }
                } else if (args.length == 4) {
                    for (Permission permission : Permission.values()) {
                        if (permission == Permission.NONE) continue;
                        tab.add(permission.name());
                    }
                } else if (args.length == 5) {
                    tab.add("true");
                    tab.add("false");
                }
            }
        }
        return tab;
    }

}
