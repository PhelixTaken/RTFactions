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

import me.phelix.rtfactions.FPlayer;
import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.RTFactions;
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class CmdChat extends SubCommand implements Listener {

    private final RTFactions plugin;
    public CmdChat(RTFactions plugin) {
        super(new String[]{"chat", "c"}, new String[]{"[p, c, a]"}, "Change chat", Permission.CHAT, true);
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            switch (fme.getChat()) {
                case 0:
                    fme.getChat('f');
                    sendMessage(Message.commandChatChange, "faction");
                    break;
                case 1:
                    fme.getChat('a');
                    sendMessage(Message.commandChatChange, "ally");
                    break;
                case 2:
                    fme.getChat('p');
                    sendMessage(Message.commandChatChange, "public");
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        final FPlayer fme = plugin.getPlayerHandler().getByPlayer(event.getPlayer());
        if (fme.hasFaction()) {
            final Faction myFaction = fme.getFaction();
            switch (fme.getChat()) {
                case 1:
                    event.setCancelled(true);
                    myFaction.broadCast(ChatColor.translateAlternateColorCodes('&', String.format(Message.commandChatFactionPrefix, fme.getName() + fme.getPrefixCharacters(), event.getMessage())));
                    break;
                case 2:
                    event.setCancelled(true);
                    final String allyMessage = ChatColor.translateAlternateColorCodes('&', String.format(Message.commandChatAllyPrefix, fme.getName() + fme.getPrefixCharacters(), event.getMessage()));
                    myFaction.getAllies(plugin.getFactionHandler()).forEach(ally -> ally.broadCast(allyMessage));
                    myFaction.broadCast(allyMessage);
                    break;
            }
        }
    }

}
