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
import me.phelix.rtfactions.utils.Config;
import me.phelix.rtfactions.utils.JsonMessage;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public final class CmdMap extends SubCommand {

    public CmdMap() {
        super(new String[]{"map"}, new String[]{""}, "Show all the chunks in a radius on a map", Permission.NONE, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sendMap();
    }

    private void sendMap() {
        final int mapRowsToUse = Config.mapRow;
        final int mapWidthToUse = Config.mapWidth;

        final FLocation fLocation = new FLocation(fme.getPlayer().getLocation());
        final FLocation start = new FLocation(fLocation.getWorldUUID().toString(),
                fLocation.getX() - mapRowsToUse,
                fLocation.getZ() + mapWidthToUse);

        final FLocation end = new FLocation(fLocation.getWorldUUID().toString(),
                fLocation.getX() + mapRowsToUse,
                fLocation.getZ() - mapWidthToUse);

        final int xStart = start.getX() > end.getX() ? end.getX() : start.getX();
        final int zStart = start.getZ() > end.getZ() ? end.getZ() : start.getZ();
        final int xEnd = start.getX() < end.getX() ? end.getX() : start.getX();
        final int zEnd = start.getZ() < end.getZ() ? end.getZ() : start.getZ();

        final Map<Faction, MapInfo> infoMap = new HashMap<>();
        int nextChar = 0;

        for (int x = xStart; x <= xEnd; x++) {
            final JsonMessage message = new JsonMessage();
            for (int z = zStart; z <= zEnd; z++) {


                final FLocation fLocation1 = new FLocation(fLocation.getWorldUUID().toString(), x, z);
                final Faction faction = chunkHandler.getFactionFromChunk(fLocation1);

                if(faction.isAlly(myFaction)) {
                    infoMap.put(faction, new MapInfo(faction, '@'));
                } else {
                    infoMap.put(faction, new MapInfo(faction, Config.mapCharacters[nextChar]));
                }
                nextChar++;
                if(nextChar > Config.mapCharacters.length)
                    nextChar = 0;

                if (x == fLocation.getX() && z == fLocation.getZ()) {
                    message.append(ChatColor.translateAlternateColorCodes('&', Config.mapPlayer)).save();
                } else {
                    if (!faction.getName().equals("Wilderness") && faction.isAlly(myFaction)) {
                        message.append(ChatColor.translateAlternateColorCodes('&', Config.mapAllyColor + "@")).save();
                    } else if (faction.getName().equals("Wilderness")) {
                        message.append(ChatColor.translateAlternateColorCodes('&', Config.mapWildernessColor + "-")).save();
                    } else if (fme.hasFaction() && faction.equals(myFaction)) {
                        message.append(ChatColor.translateAlternateColorCodes('&', Config.mapSelfColor)).save();
                    } else {
                        message.append(ChatColor.RED + "%").save();
                    }

                }

            }
            message.send(fme.getPlayer());
        }

    }

}

final class MapInfo {

    private final Faction faction;
    private final char symbol;

    public MapInfo(Faction faction, char symbol) {
        this.faction = faction;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public Faction getFaction() {
        return faction;
    }




}
