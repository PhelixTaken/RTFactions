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
import me.phelix.rtfactions.utils.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class CommandHandler implements CommandExecutor {

    private final Set<SubCommand> commands = new HashSet<>();
    private final RTFactions plugin;

    public CommandHandler(RTFactions plugin) {
        this.plugin = plugin;
        commands.add(new CmdCreateFaction());
        commands.add(new CmdInvite());
        commands.add(new CmdJoin());
        commands.add(new CmdWho());
        commands.add(new CmdPerms(plugin));
        commands.add(new CmdDefaultRole());
        commands.add(new CmdAddAlly());
        commands.add(new CmdDescription());
        commands.add(new CmdSetWarp());
        commands.add(new CmdWarp());
        commands.add(new CmdClaim());
        commands.add(new CmdUnclaim());
        commands.add(new CmdUnclaimAll());
        commands.add(new CmdDeleteWarp());
        commands.add(new CmdDisband());
        commands.add(new CmdSetHome());
        commands.add(new CmdHome());
        commands.add(new CmdChat(plugin));
        commands.add(new CmdRemoveAlly());
        commands.add(new CmdPromote());
        commands.add(new CmdDemote());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("f")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player to perform this action!");
                return true;
            }

            if (args.length >= 1) {

                for (SubCommand subCommand : commands) {

                    for (int i = 0; i < subCommand.getAliases().length; i++) {

                        if (subCommand.getAliases()[i].equalsIgnoreCase(args[0])) {

                            if (subCommand.setCommandSender(sender, plugin, plugin.getPlayerHandler(), plugin.getFactionHandler(), plugin.getChunkHandler())) {

                                if (subCommand.hasPermission()) {
                                    subCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
                                }
                            }
                        }
                    }
                }
            }

        }

        return true;
    }
}
