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

import me.phelix.rtfactions.interfaces.ChunkHandler;
import me.phelix.rtfactions.interfaces.FactionHandler;
import me.phelix.rtfactions.interfaces.PlayerHandler;
import org.bukkit.plugin.java.JavaPlugin;

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

    private void load() {

    }

    private void save() {

    }

    public FactionHandler getFactionHandler(){
        return factionHandler;
    }

    public PlayerHandler getPlayerHandler(){
        return playerHandler;
    }

    public ChunkHandler getChunkHandler(){
        return chunkHandler;
    }

}
