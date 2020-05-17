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

import me.phelix.rtfactions.utils.Config;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;

public final class CmdClaim extends SubCommand {

    public CmdClaim(){
        super(new String[]{"claim", "c"}, new String[]{"[x] [z]"}, "Claim a chunk for the faction", Permission.CLAIMING, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){

        if(myFaction.getPowerLeft() - Config.claimPower < 0) {
            sendMessage(Message.commandClaimNotEnoughPower);
            return;
        }

        if(args.length == 0) {
            final Chunk chunk = fme.getPlayer().getLocation().getChunk();

            if(!chunkHandler.isClaimed(chunk)) {
                chunkHandler.claimChunk(chunk, myFaction);
                sendMessage(Message.commandClaimSuccessfully, chunk.getX(), chunk.getZ());
            } else if(chunkHandler.getFactionFromChunk(chunk).equals(myFaction)) {
                sendMessage(Message.commandClaimSelf);
            } else {
                sendMessage(Message.commandClaimEnemy, chunkHandler.getFactionFromChunk(chunk).getName());
            }
        } else if(args.length == 2) {

        } else {
            sendMessage(toString());
        }

    }

}
