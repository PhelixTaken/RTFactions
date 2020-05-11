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

package me.phelix.rtfactions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.phelix.rtfactions.handlers.MemoryChunkHandler;
import me.phelix.rtfactions.handlers.MemoryFactionHandler;
import me.phelix.rtfactions.handlers.MemoryPlayerHandler;
import me.phelix.rtfactions.implementations.MemoryFPlayer;
import me.phelix.rtfactions.interfaces.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public final class RTFactions extends JavaPlugin {

    private FactionHandler factionHandler;
    private PlayerHandler playerHandler;
    private ChunkHandler chunkHandler;

    public void onEnable() {
        load();
    }

    public void onDisable() {
        save();
    }

    public FactionHandler getFactionHandler() {
        return factionHandler;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public ChunkHandler getChunkHandler() {
        return chunkHandler;
    }

    private void load() {
        final File factionFile = new File(getDataFolder() + File.separator + "Factions", "factions.json");
        final File worldFile = new File(getDataFolder() + File.separator + "Factions", "world.json");

        if (!factionFile.exists()) return;

        try {
            final Gson gson = new Gson();
            final FileReader factionReader = new FileReader(factionFile);

            factionHandler = new MemoryFactionHandler();
            chunkHandler = new MemoryChunkHandler(this);
            playerHandler = new MemoryPlayerHandler();
            final Type factionType = new TypeToken<HashMap<String, Faction>>(){}.getType();
            factionHandler.getFactionMap().putAll((gson.fromJson(factionReader, factionType)));

            for(final Faction faction : factionHandler.getFactionMap().values()){
                for(final FPlayer fPlayer : faction.getPlayers()) {
                    fPlayer.setFaction(faction);
                    playerHandler.getPlayerMap().put(fPlayer.getUUID(), (MemoryFPlayer) fPlayer);
                }
            }

            if (worldFile.exists()) {
                final Type worldType = new TypeToken<HashMap<FLocation, Faction>>(){}.getType();
                final FileReader worldReader = new FileReader(worldFile);
                chunkHandler.getChunkMap().putAll(gson.fromJson(worldReader, worldType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void save() {

    }



}
