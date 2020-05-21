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

import me.phelix.rtfactions.FLocation;
import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdClaimAt extends SubCommand {

    public CmdClaimAt(){
        super(new String[]{"claimat"}, new String[]{"<x> <z>"}, "", Permission.CLAIMING, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 2) {
            final int x = Integer.parseInt(args[0]);
            final int z = Integer.parseInt(args[1]);
            final FLocation fLocation = new FLocation(fme.getPlayer().getLocation().getWorld().getUID().toString(), x, z);
            final Faction faction = chunkHandler.getFactionFromChunk(fLocation);
            if(!faction.getName().equals("Wilderness")) {
                if(faction.equals(myFaction)) {
                    sendMessage(Message.commandClaimSelf);
                } else {
                    sendMessage(Message.commandClaimEnemy, faction.getName());
                }
            } else {
                chunkHandler.claimChunk(fLocation, myFaction);
            }
        }
    }

}
