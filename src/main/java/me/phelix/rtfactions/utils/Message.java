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

    public static String commandInfoFactionNotExist = "&6%s &7does not exists!";
    public static String commandInfoPlayerNotExist = "&6%s &7does not exist!";

    public static String commandHelp = "&6f %s &8> &7";

    public static String commandPlayerAlreadyInFaction = "&6%s &7is already in a faction!";
    public static String commandPlayerFactionNeeded = "&7You need to be in a faction to do that!";
    public static String commandPlayerSelfAlreadyInFaction = "&7You are already in a faction!";

    public static String commandCreateFaction = "&7Successfully created &6%s";
    public static String commandCreateAlreadyInFaction = "&7You are already in a faction!";
    public static String commandCreateFactionAlreadyExists = "&6%s &7already exists!";
    public static String commandCreateNameLength = "&7The name must be at least &6%s &7characters long and can have a maximum of &6%s &7characters!";

    public static String commandInvitePlayerNotExist = "&6%s &7does not exist or is not online!";
    public static String commandInvitePlayerAlreadyInSameFaction = "&6%s &7is already in the faction!";
    public static String commandInviteSuccessfullyInvited = "&7Successfully invited &6%s";
    public static String commandInviteSuccessfullyInvitedPlayer = "&6%s &7has invited you. Click here to join &6%s&7!";

    public static String commandPermAdded = "&7You have added the permission &6%s &7to &6%s&7!";
    public static String commandPermRemoved = "&7You have removed the permission &6%s &7from &6%s&7!";
    public static String commandPermRoleNotExist = "&7The permission &6%s &7or the role &6%s &7does not exist!";

    public static String commandDRoleLeader = "&7You can't set the default role to leader!";
    public static String commandDRoleSet = "&7Successfully set the default role to &6%s";
    public static String commandDRoleNotExist = "&7The role &6%s &7does not exist!";

    public static String commandAllySent = "&7Successfully sent an ally request to &6%s&7!";
    public static String commandAllyRequest = "&6%s &7wants to be an ally! Click here to be an ally!";
    public static String commandAllyAlready = "&7Your faction is already an ally with &6%s&7!";
    public static String commandAllySelf = "&7You can't ally your self!";
    public static String commandAllySuccess = "&7Your faction is now an ally with &6%s&7!";

    public static String commandDescriptionSet = "You have updated your factions description to &6%s";

    public static String commandJoinFactionBroadcast = "&6%s &7has joined the faction!";
    public static String commandJoinFactionPlayer = "&7You have successfully joined &6%s&7!";
    public static String commandJoinFactionClosed = "&6%s &7is closed!";

    public static String commandSetWarpExists = "&6%s &7already exists as a warp!";
    public static String commandSetWarpTerritory = "&7You can't create a warp in &6enemy territory&7!";
    public static String commandSetWarpWithPassword = "&7Created the warp &6%s &7with password as &6%s";
    public static String commandSetWarpWithoutPassword = "&7Created the warp &6%s";

    public static String commandWarpNotExist = "&7The warp &6%s &7does not exist!";
    public static String commandWarpRequiresPassword = "&7The warp &6%s &7requires a password!";
    public static String commandWarpSuccessful = "&7Successfully warped to &6%s&7!";
    public static String commandWarpWrongPassword = "&6%s &7doesn't match the password!";

    public static String commandClaimNotEnoughPower = "&7You don't have enough power to claim!";
    public static String commandClaimSuccessfully = "&7Claimed the chunk &6%s&7;&6%s&7";
    public static String commandClaimSelf = "&7This chunk is already claimed by your faction!";
    public static String commandClaimEnemy = "&7This chunk is already claimed by &6%s&7!";

    public static String commandUnclaimWilderness = "&7You can't unclaim wilderness!";
    public static String commandUnclaimEnemy = "You can't unclaim &6%s's &7chunk!";
    public static String commandUnclaimSuccessful = "&7Unclaimed the chunk &6%s&7;&6%s";

    public static String commandUnclaimAllNone = "&7Your faction doesn't have any claimed chunks to unclaim!";
    public static String commandUnclaimAllSuccessful = "&7Unclaimed all the chunks from your faction!";

    public static String commandDeleteWarpSuccess = "&7Deleted the warp &6%s&7!";

    public static String commandDisbandWarning = "&7Click here to disband your faction. &o&6(( This cannot be undone! ))";
    public static String commandDisbandBroadcast = "&7The faction is disbanded by &6%s&7!";
    public static String commandDisbandSuccess = "&7You have disbanded the faction &6%s&7!";

    public static String commandSetHomeSuccessful = "&7Set a home at &6%s&7;&6%s&7;&6%s";
    public static String commandSetHomeEnemy = "&7You can only set a home in your &6claimed &7chunks!";

    public static String commandHomeSuccessful = "&7Teleported to home";
    public static String commandHomeNotExist = "&7Your faction does not have a home set!";

    public static String commandChatChange = "&7Xhanged your chat to &6%s &7chat!";

    public static String commandChatFactionPrefix = "&f%s >> &a%s";
    public static String commandChatAllyPrefix = "&f%s >> &d%s";

    public static String commandRemoveAllyNoAlly = "&7Your faction is not allied with &6%s&7!";
    public static String commandRemoveAllyBroadcast = "&6%s &7is not an ally anymore!";
    public static String commandRemoveAllySuccessful = "&7You have successfully de-allied &6%s&7!";

    public static String commandPromoteNotSameFaction = "&7You can't promote people that aren't in your faction!";
    public static String commandPromoteNotHigher = "&7You can't promote someone to a higher role than yours!";
    public static String commandPromoteSuccessful = "&7You have promoted &6%s &7to &6%s&7!";
    public static String commandPromotePlayer = "&7You are promoted to &6%s &7by &6%s&7!";

    public static String commandDemoteNotSameFaction = "&7You can't demote people that aren't in your faction!";
    public static String commandDemoteHigherRole = "&7You cannot demote someone that has the same or higher role than you!";
    public static String commandDemoteUnderRecruit = "&7You cannot demote someone that has the &6recruit &7role!";
    public static String commandDemoteSuccessful = "&7You have demoted &6%s &7to &6%s&7!";
    public static String commandDemotePlayer = "&7You are demoted to &6%s &7by &6%s&7!";

    public static String commandKickNotSameFaction = "&7You can't kick people that aren't in your faction!";
    public static String commandKickHigherRole = "&7You can't kick someone that has the same or higher role than you!";
    public static String commandKickSuccessful = "&7You have kicked &6%s &7from the faction!";
    public static String commandKickPlayer = "&7You have been kicked from &6%s &7by &6%s&7!";
    public static String commandKickBroadcast = "&6%s &7has been kicked from the faction by &6%s&7!";


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
