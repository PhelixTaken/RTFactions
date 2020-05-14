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
import me.phelix.rtfactions.utils.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CmdWho extends SubCommand {

    public CmdWho(){
        super(new String[]{"who", "info", "show"}, new String[]{"[faction]"}, "Shows the faction information", Permission.NONE, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        Faction faction = null;
        if(args.length == 1)
            faction = myFaction;
        else if(args.length == 2) {
            faction = plugin.getFactionHandler().getByName(args[1]);
            if(faction == null){
                sendMessage(Message.show_not_exist, args[1]);
                return;
            }
        }
        else {
            fme.sendMessage(toString());
            return;
        }

        if(faction == null || faction.getName().equalsIgnoreCase("Wilderness")){
            sendMessage(Message.commandPlayerFactionNeeded);
            return;
        }

        final Set<String> names = new HashSet<>();
        faction.getPlayers().forEach(fPlayer -> {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(fPlayer.getUUID());
            names.add(offlinePlayer.getName());
        });
        final List<String> msges = new ArrayList<>();
        final List<String> allies = new ArrayList<>();

        for(final Faction ally : faction.getAllies()){
            allies.add(ally.getName());
        }

        for(String string : Message.show){
            final String str = string.replace("{faction_name}", faction.getName())
                    .replace("{faction_leader}", faction.getLeader().getPlayer().getName())
                    .replace("{faction_members}", names.toString()
                            .replace("[", "").replace("]", ""))
                    .replace(",", "&7,&6")
                    .replace("{faction_claims}", faction.getClaims().size() + "")
                    .replace("{faction_totalclaims}", faction.getTotalPower() + "")
                    .replace("{faction_powerleft}", faction.getPowerLeft() + "")
                    .replace("{faction_totalpower}", faction.getTotalPower() + "")
                    .replace("{faction_allies}", allies.toString().replace("[", "").replace("]", ""))
                    .replace("{faction_description}", faction.getDescription());
            ;

            msges.add(str);
        }

        msges.forEach(this::sendMessage);
    }

}
