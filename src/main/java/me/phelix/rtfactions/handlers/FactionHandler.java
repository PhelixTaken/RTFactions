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

import me.phelix.rtfactions.Faction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class FactionHandler {

    private final Map<String, Faction> factionMap = new HashMap<>();
    public static final String WILDERNESS = "Wilderness";

    /**
     * Holds all the factions.
     * You can get it with the faction name.
     * Holds Faction as value.
     *
     * @return factionMap
     */
    @NotNull
    public final Map<String, Faction> getFactionMap() {
        return factionMap;
    }

    /**
     * Gets the faction with a name.
     *
     * @param name The faction name that you want to get.
     * @return Faction that exists.
     */
    @Nullable
    public final Faction getByName(String name) {
        return factionMap.get(name);
    }

    public Faction getWilderness() {
        return factionMap.get(WILDERNESS);
    }

}
