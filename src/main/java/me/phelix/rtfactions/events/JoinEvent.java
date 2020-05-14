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

package me.phelix.rtfactions.events;

import me.phelix.rtfactions.FPlayer;
import me.phelix.rtfactions.Faction;
import me.phelix.rtfactions.RTFactions;
import me.phelix.rtfactions.utils.Role;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class JoinEvent implements Listener {

    private final RTFactions plugin;

    public JoinEvent(RTFactions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!plugin.getPlayerHandler().getPlayerMap().containsKey(player.getUniqueId())) {
            final FPlayer fPlayer = new FPlayer(player.getUniqueId().toString());
            plugin.getPlayerHandler().getPlayerMap().put(player.getUniqueId(), fPlayer);
            final Faction faction = plugin.getFactionHandler().getWilderness();
            fPlayer.setRole(Role.NONE);
            fPlayer.setFaction(faction);
            faction.addPlayer(fPlayer);
        }
    }

}
