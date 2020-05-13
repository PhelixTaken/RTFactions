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

import com.google.gson.reflect.TypeToken;
import me.phelix.rtfactions.commands.CommandHandler;
import me.phelix.rtfactions.events.JoinEvent;
import me.phelix.rtfactions.handlers.ChunkHandler;
import me.phelix.rtfactions.handlers.FactionHandler;
import me.phelix.rtfactions.handlers.PlayerHandler;
import me.phelix.rtfactions.utils.Message;
import net.prosavage.baseplugin.serializer.Persist;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;

public final class RTFactions extends JavaPlugin {

    private FactionHandler factionHandler;
    private PlayerHandler playerHandler;
    private ChunkHandler chunkHandler;
    private final Message message = new Message();

    public void onEnable() {
        getCommand("f").setExecutor(new CommandHandler(this));

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        registerEvents();
        load();
    }

    public void onDisable() {
        save();
        System.out.println("Saving RTFactions");
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new JoinEvent(this), this);
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
        final File messagesFile = new File(getDataFolder() + File.separator + "Factions", "messages.json");

        final Persist persist = new Persist();

        factionHandler = new FactionHandler();
        chunkHandler = new ChunkHandler(this);
        playerHandler = new PlayerHandler();

        final Faction wilderness = new Faction("Wilderness");
        final Faction safeZone = new Faction("Safezone");
        final Faction warZone = new Faction("Warzone");
        getFactionHandler().getFactionMap().put(wilderness.getName(), wilderness);
        getFactionHandler().getFactionMap().put(safeZone.getName(), safeZone);
        getFactionHandler().getFactionMap().put(warZone.getName(), warZone);

        if (messagesFile.exists())
            message.load(persist, messagesFile);

        if (!factionFile.exists()) return;

        final HashMap<String, Faction> factionMap = persist.load(new TypeToken<HashMap<String, Faction>>() {
        }.getType(), factionFile);

        factionHandler.getFactionMap().putAll(factionMap);

        for (final Faction faction : factionHandler.getFactionMap().values()) {
            for (final FPlayer fPlayer : faction.getPlayers()) {
                fPlayer.setFaction(faction);
                getPlayerHandler().getPlayerMap().put(fPlayer.getUUID(), fPlayer);
            }
        }

        if (worldFile.exists()) {
            final Type worldType = new TypeToken<HashMap<FLocation, Faction>>() {
            }.getType();
            chunkHandler.getChunkMap().putAll(persist.load(worldType, worldFile));
            for (final FLocation fLocation : chunkHandler.getChunkMap().keySet()) {
                final Faction faction = chunkHandler.getChunkMap().get(fLocation);
                faction.addClaim(fLocation);
            }
        }


    }

    private void save() {
        final File factionFile = new File(getDataFolder() + File.separator + "Factions", "factions.json");
        final File worldFile = new File(getDataFolder() + File.separator + "Factions", "world.json");
        final File messageFile = new File(getDataFolder() + File.separator + "Factions", "messages.json");

        final Persist persist = new Persist();

        try {
            final File file = new File(getDataFolder() + File.separator + "Factions");
            file.mkdirs();
            factionFile.createNewFile();
            worldFile.createNewFile();
            messageFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        persist.save(false, factionHandler.getFactionMap(), factionFile);
        persist.save(false, chunkHandler.getChunkMap(), worldFile);

        message.save(persist, messageFile);
    }



}
