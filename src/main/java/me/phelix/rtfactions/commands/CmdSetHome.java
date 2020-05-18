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
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

public final class CmdSetHome extends SubCommand {

    public CmdSetHome() {
        super(new String[]{"sethome", "seth", "sh"}, new String[]{""}, "Set a home in your faction", Permission.SET_HOME, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final Location location = fme.getPlayer().getLocation();
        int test = 0;
        if (args.length == 0) {
            if (chunkHandler.getFactionFromChunk(location).equals(myFaction)) {
                myFaction.setHome(location);
                sendMessage(Message.commandSetHomeSuccessful, location.getX(), location.getY(), location.getZ());
            } else {
                sendMessage(Message.commandSetHomeEnemy);
            }
        } else {
            sendMessage(toString());
        }

    }

}
