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

package me.phelix.rtfactions.handlers;

import me.phelix.rtfactions.implementations.MemoryFPlayer;
import me.phelix.rtfactions.interfaces.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class MemoryPlayerHandler implements PlayerHandler {

    private final Map<UUID, MemoryFPlayer> playerMap = new HashMap<>();

    /**
     * Holds all the players.
     * You can get it with an UUID.
     * Holds FPlayer as value.
     *
     * @return playerMap
     */
    @Override
    @NotNull
    public final Map<UUID, MemoryFPlayer> getPlayerMap() {
        return playerMap;
    }

    /**
     * Get the FPlayer by a string.
     *
     * @param id The id that is being used
     * @return FPlayer
     */
    @Override
    @Nullable
    public final MemoryFPlayer getByID(String id) {
        return playerMap.get(UUID.fromString(id));
    }

    /**
     * Returns FPlayer with an online player.
     * If the player is offline it will return null.
     *
     * @param player The online player
     * @return FPlayer
     */
    @Override
    @Nullable
    public final MemoryFPlayer getByPlayer(Player player) {
        return playerMap.get(player.getUniqueId());
    }

    /**
     * Deprecated since it uses OfflinePlayer.
     * Get the player with name.
     * There is a bigger change with this
     * to return null.
     *
     * @param name The name of the player
     * @return FPlayer
     */
    @Deprecated
    @Override
    @Nullable
    public final MemoryFPlayer getByName(String name) {
        return playerMap.get(Objects.requireNonNull(Bukkit.getOfflinePlayer(name)).getUniqueId());
    }
}