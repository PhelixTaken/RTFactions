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
import me.phelix.rtfactions.utils.Message;
import me.phelix.rtfactions.utils.commands.SubCommand;
import me.phelix.rtfactions.utils.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.*;

public final class CmdMap extends SubCommand {

    public CmdMap() {
        super(new String[]{"map"}, new String[]{""}, "Show all the chunks in a radius on a map", Permission.NONE, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sendMap(fme.getFaction());
    }

    private void sendMap(Faction faction1) {
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
        fme.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Message.commandMapPrefix));

        for (int x = xStart; x <= xEnd; x++) {
            final JsonMessage message = new JsonMessage();
            for (int z = zStart; z <= zEnd; z++) {


                final FLocation fLocation1 = new FLocation(fLocation.getWorldUUID().toString(), x, z);
                final Faction faction = chunkHandler.getFactionFromChunk(fLocation1);

                if (!infoMap.containsKey(faction)) {
                    if (!faction.getName().equals("Wilderness") && faction.isAlly(faction1)) {
                        infoMap.put(faction, new MapInfo(faction, '@'));
                    } else {
                        infoMap.put(faction, new MapInfo(faction, Config.mapCharacters[nextChar]));
                    }
                }
                nextChar++;
                if (nextChar >= Config.mapCharacters.length)
                    nextChar = 0;

                final MapInfo mapInfo = infoMap.get(faction);

                if (x == fLocation.getX() && z == fLocation.getZ()) {
                    message.append(ChatColor.translateAlternateColorCodes('&', Config.mapPlayer)).save();
                } else {
                    if (!faction.getName().equals("Wilderness") && faction.isAlly(faction1)) {
                        message.append(ChatColor.LIGHT_PURPLE + "@").setHoverAsTooltip(String.join("\n" ,getLore(faction))).save();
                    } else if (faction.getName().equals("Wilderness")) {
                        message.append(ChatColor.translateAlternateColorCodes('&', Config.mapWildernessColor + "-"))
                                .setHoverAsTooltip(String.join("\n", getLore(faction)))
                                .setClickAsExecuteCmd("/f claimat " + x + " " + z)
                                .save();
                    } else if (fme.hasFaction() && faction.equals(faction1)) {
                        message.append(ChatColor.translateAlternateColorCodes('&', Config.mapSelfColor))
                                .setHoverAsTooltip(String.join("\n" ,getLore(faction)))
                                .setClickAsExecuteCmd("/f claimat " + x + " " + z + " false")
                                .save();
                    } else {
                        message.append(mapInfo.getColor().toString() + mapInfo.getSymbol()).setHoverAsTooltip(String.join("\n" ,getLore(faction))).save();
                    }

                }

            }
            message.send(fme.getPlayer());
        }


        final Set<String> factionMap = new HashSet<>();

        infoMap.forEach((faction, mapInfo) -> {

            if(faction.getName().equals("Wilderness")) return;
            if(mapInfo.getFaction().equals(faction1)) return;

            if(faction.isAlly(faction1)) {
                factionMap.add(ChatColor.translateAlternateColorCodes('&', Config.mapAllyColor + mapInfo.getSymbol() + " " + mapInfo.getFaction().getName()) );
                return;
            }

            factionMap.add(mapInfo.getColor().toString() + mapInfo.getSymbol() + " " + mapInfo.getFaction().getName());

        });

        fme.sendMessage(color(factionMap.toString().replace("[", "").replace("]", "")));

    }

    private List<String> getLore(Faction faction) {
        final List<String> lore = new ArrayList<>();
        if (faction.getName().equals("Wilderness")) {
            lore.add(ChatColor.GRAY + "Tag » " + ChatColor.GREEN.toString() + ChatColor.BOLD + faction.getName());

            if (fme.hasFaction()) {
                lore.add("");
                lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.ITALIC + "(( Click to claim ))");
            }
        } else {
            lore.add(ChatColor.GRAY + "Tag » " + ChatColor.GOLD + faction.getName());
            lore.add(ChatColor.GRAY + "Leader » " + ChatColor.GOLD + faction.getLeader().getName());
            lore.add(ChatColor.GRAY + "Claims » " + ChatColor.GOLD + faction.getClaims().size() + ChatColor.GRAY + "/" + ChatColor.GOLD + faction.getTotalPower() / Config.factionClaimPower);
            lore.add(ChatColor.GRAY + "Power » " + ChatColor.GOLD + faction.getPowerLeft() + ChatColor.GRAY + "/" + ChatColor.GOLD + faction.getTotalPower());
            if (faction.equals(fme.getFaction())) {
                lore.add("");
                lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.ITALIC + "(( Click to unclaim ))");
            }
        }
        return lore;
    }



}

final class MapInfo {

    private final Faction faction;
    private final char symbol;
    private ChatColor color;

    public MapInfo(Faction faction, char symbol) {
        this.faction = faction;
        this.symbol = symbol;
        color = ChatColor.getByChar(Integer.toHexString(new Random().nextInt(16)));
        while (color == ChatColor.GREEN || color == ChatColor.DARK_GRAY || color == ChatColor.BLACK) {
            color = ChatColor.getByChar(Integer.toHexString(new Random().nextInt(16)));
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public Faction getFaction() {
        return faction;
    }

    public ChatColor getColor() {
        return color;
    }


}
