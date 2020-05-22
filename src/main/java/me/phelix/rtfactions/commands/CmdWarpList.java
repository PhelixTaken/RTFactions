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

import me.phelix.rtfactions.utils.JsonMessage;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.Role;
import me.phelix.rtfactions.utils.Warp;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

public final class CmdWarpList extends SubCommand {

    public CmdWarpList(){
        super(new String[]{"warplist", "warpl", "wl"}, new String[]{""}, "A list of the faction warps", Permission.WARP_LIST, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 0) {
            final JsonMessage message = new JsonMessage();
            message.append(ChatColor.translateAlternateColorCodes('&', Message.prefix + " ")).save();

            String comma = ", ";
            int i = 0;

            for(final Warp warp : myFaction.getWarps().values()) {
                i++;
                if(i == myFaction.getWarps().size())
                    comma = "";
                if(warp.getName().equalsIgnoreCase("home")) continue;
                if(warp.hasPassword() && fme.getRole() == Role.LEADER) {
                    message.append(ChatColor.translateAlternateColorCodes('&',"&7" + warp.getName() + comma)).setHoverAsTooltip(warp.getPassword()).save();
                } else {
                    message.append(ChatColor.translateAlternateColorCodes('&',"&7" + warp.getName() + comma)).save();
                }
            }

            message.send(fme.getPlayer());
        } else {
            sendMessage(toString());
        }
    }

}
