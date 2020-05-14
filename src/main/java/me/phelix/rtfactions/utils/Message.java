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

package me.phelix.rtfactions.utils;

import net.prosavage.baseplugin.serializer.Persist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class Message {

    private transient Message instance = this;

    public static String prefix ="&7[&6!&7]";

    public static String commandFactionNeeded = "&7You must be in a faction to do that!";
    public static String commandPermissionNeeded = "&7Your faction does not allow you to do this, it requires the action &6%s&7!";

    public static String command_help = "&6f %s &8> &7";

    public static String commandPlayerAlreadyInFaction = "&6%s &7is already in a faction!";
    public static String commandPlayerFactionNeeded = "&7You need to be in a faction to do that!";

    public static String commandCreatedFaction = "&7Successfully created &6%s";
    public static String commandCreatedAlreadyInFaction = "&7You are already in a faction!";
    public static String commandCreatedFactionAlreadyExists = "&6%s &7already exists!";

    public static String commandInvitePlayerNotExist = "&6%s &7does not exist or is not online!";
    public static String commandInvitePlayerAlreadyInSameFaction = "&6%s &7is already in the faction!";
    public static String commandInviteSuccessfullyInvited = "&7Successfully invited &6%s";
    public static String commandInviteSuccessfullyInvitedPlayer = "&6%s &7has invited you. Click here to join &6%s&7!";

    public static String commandInfoFactionNotExist = "&6%s &7does not exists!";




    public static String show_not_exist = "&6%s &7does not exist!";


    public static String faction_join_broadcast = "&6%s &7has joined the faction!";
    public static String faction_join_player = "&7You have successfully joined &6%s&7!";
    public static String faction_closed = "&6%s &7is closed!";


    public static List<String> show = new ArrayList<String>() {{
        add("&8&l&m===========================================");
        add("&7Tag \u00BB &6{faction_name}");
        add("&7Description \u00BB &6{faction_description}");
        add("&7Leader \u00BB &6{faction_leader}");
        add("&7Members \u00BB &6{faction_members}");
        add("&7Claims \u00BB &6{faction_claims}&7/&6{faction_totalclaims}");
        add("&7Power \u00BB &6{faction_powerleft}&7/&6{faction_totalpower}");
        add("&7Allies \u00BB &6{faction_allies}");
        add("&8&l&m===========================================");
    }};

    public void save(Persist persist, File file){
        persist.save(false, instance, file);
    }

    public void load(Persist persist, File file){
        persist.load(Message.class, file);
    }

}
