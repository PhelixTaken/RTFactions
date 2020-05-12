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

package me.phelix.rtfactions.utils.permission;

import me.phelix.rtfactions.utils.Role;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class FactionPermission {

    private final Map<Role, Collection<Permission>> permissionMap = new HashMap<>();

    public Collection<Permission> getPermissions(Role role) {
        return permissionMap.get(role);
    }

    public void removePermission(Role role, Permission permission) {
        permissionMap.get(role).remove(permission);
    }

    public void addPermission(Role role, Permission permission) {
        if(hasPermission(role, permission)) return;
        permissionMap.get(role).add(permission);
    }

    public boolean hasPermission(Role role, Permission permission) {
        return permissionMap.get(role).contains(permission);
    }

    public void setDefaultPermissions() {

        final Role recruit = Role.RECRUIT;
        final Role member = Role.MEMBER;
        final Role moderator = Role.MODERATOR;
        final Role coleader = Role.COLEADER;
        final Role leader = Role.LEADER;


        permissionMap.put(recruit, new HashSet<>());
        permissionMap.put(member, new HashSet<>());
        permissionMap.put(moderator, new HashSet<>());
        permissionMap.put(coleader, new HashSet<>());
        permissionMap.put(leader, new HashSet<>());


        for (final Permission permission : Permission.values()) {

            switch (permission) {
                case NONE:
                case CHAT:
                    permissionMap.get(recruit).add(permission);
                    permissionMap.get(member).add(permission);
                    permissionMap.get(moderator).add(permission);
                    permissionMap.get(coleader).add(permission);
                    permissionMap.get(leader).add(permission);
                    break;
                case PLACING:
                case BREAKING:
                case CLAIMING:
                case UNCLAIMING:
                case WARP:
                case HOME:
                    permissionMap.get(member).add(permission);
                    permissionMap.get(moderator).add(permission);
                    permissionMap.get(coleader).add(permission);
                    permissionMap.get(leader).add(permission);
                    break;
                case KICK:
                case INVITING:
                    permissionMap.get(moderator).add(permission);
                    permissionMap.get(coleader).add(permission);
                    permissionMap.get(leader).add(permission);
                    break;
                case UNCLAIMING_ALL:
                case DEMOTE:
                case PROMOTE:
                case SET_WARP:
                case DELETE_WARP:
                case SET_DESCRIPTION:
                case ADD_ALLY:
                case REMOVE_ALLY:
                case SET_OPEN:
                case SET_HOME:
                    permissionMap.get(coleader).add(permission);
                    permissionMap.get(leader).add(permission);
                    break;
                case DISBAND:
                case DEFAULT_ROLE:
                case PERMS:
                    permissionMap.get(leader).add(permission);

            }

        }

    }

}
