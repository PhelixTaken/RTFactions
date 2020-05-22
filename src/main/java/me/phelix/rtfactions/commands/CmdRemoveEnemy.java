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

import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.command.CommandSender;

public final class CmdRemoveEnemy extends SubCommand {

    public CmdRemoveEnemy(){
        super(new String[]{"removeenemy", "deenemy", "de"}, new String[]{"<faction>"}, "Remove an enemy faction from your faction", Permission.REMOVE_ENEMY, true);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 1) {

            final Faction faction = factionHandler.getByName(args[0]);

            if(faction == null) {
                sendMessage(Message.commandInfoFactionNotExist, args[0]);
                return;
            }

            if(!myFaction.isEnemy(faction)) {
                sendMessage(Message.commandEnemyRemoveNot, faction.getName());
                return;
            }

            myFaction.removeEnemy(faction);
            faction.removeEnemy(myFaction);
            myFaction.broadCast(color(String.format(Message.commandEnemyRemoveBroadcast, faction.getName())));
            faction.broadCast(color(String.format(Message.commandEnemyRemoveBroadcast, myFaction.getName())));
        } else {
            sendMessage(toString());
        }
    }

}
