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

import java.util.ArrayList;
import java.util.List;

public final class Message {

    public static String prefix = "&7[&6!&7]";
    public static String command_faction_needed = "&7You must be in a faction to do that!";
    public static String command_perm_needed = "&7Your faction does not allow you to do this, it requires the action &6%s&7!";
    public static String command_help = "&6f %s &8> &7";
    public static String successfully_created_faction = "&7Successfully created &6%s";
    public static String already_in_faction = "&7You are already in a faction!";
    public static String faction_already_exists = "&6%s &7already exists!";
    public static String faction_not_exist = "&6%s &7does not exist!";
    public static String player_does_not_exist = "&6%s &7does not exist or is not online!";
    public static String player_already_in_same_faction = "&6%s &7is already in the faction!";
    public static String player_already_in_faction = "&6%s &7is already in a faction!";
    public static String player_not_same_faction = "&6%s &7is not in your faction!";
    public static String player_faction_needed = "&7You need to be in a faction to do that!";
    public static String successfully_claimed = "&7Successfully claimed &6%s ; %s&7!";
    public static String faction_already_claimed = "&7The chunk is already claimed by your faction!";
    public static String enemy_already_claimed = "&7The chunk is already claimed by &6%s&7!";
    public static String not_enough_power_claim = "&7You don't have enough power to claim!";
    public static String invalid_number_location = "&6%s &7and or &6%s &7is not a valid number!";
    public static String unclaimed_successfully = "&7Successfully unclaimed &6%s ; %s";
    public static String unclaimed_none = "&7This chunk is not claimed by anyone!";
    public static String unclaimed_other_faction = "&7This chunk is not claimed by your faction!";
    public static String unclaim_all_successful = "&7Successfully unclaimed &6all chunks!";
    public static String unclaim_all_none = "&7The faction doesn't have any claimed chunks!";
    public static String defaultrole_leader = "&7You can't set the default role to leader!";
    public static String defaultrole_successfully_set = "&7Successfully set the default role to &6%s";
    public static String defaultrole_not_exist = "&7The role &6%s &7does not exist!";
    public static String setwarp_name_exists = "%s &7already exists as a warp!";
    public static String setwarp_territory = "&7You can't create a warp in territory!";
    public static String setwarp_with_password = "&7Successfully created the warp &6%s &7with the password &6%s";
    public static String setwarp_without_password = "&7Successfully created the warp &6%s";
    public static String warp_not_exist = "%s &7does not exist!";
    public static String warp_password_required = "&7This warp has a password!";
    public static String warp_successful = "&7Successfully warped to &6%s";
    public static String warp_wrong_password = "&7The password does not match!";
    public static String deletewarp_not_exist = "&6%s &7does not exist!";
    public static String deletewarp_successful = "&7Successfully removed the warp &6%s";
    public static String promote_not_higher = "&7You cannot promote someone to your role or higher!";
    public static String promote_successfully = "&7Promoted &6%s &7to &6%s &7!";
    public static String promote_successfully_player = "&7You are promoted to &6%s &7by &6%s &7!";
    public static String demote_higher_role = "&7Cannot demote someone that has a higher role than you!";
    public static String demote_under_role = "&7Cannot demote someone under recruit!";
    public static String demote_successful = "&7Demoted &6%s &7to &6%s &7!";
    public static String demote_target_successful = "&7You are demoted to &6%s &7 by &6%s &7!";
    public static String succesfully_invited = "&7Successfully invited &6%s";
    public static String successfully_invited_player = "&6%s &7has invited you. Click here to join &6%s&7!";
    public static String successfully_joined_broadcast = "&6%s &7has joined the faction!";
    public static String succesfully_joined_player = "&7You have successfully joined &6%s&7!";
    public static String kick_higher_role = "&7You can't kick someone with the same role or a higher role than you have!";
    public static String kick_successfully_player = "&7You have been kicked by &6%s &7from &6%s&7!";
    public static String kick_broadcast = "&6%s &7has been kicked from the faction by &6%s&7!";
    public static String kick_successful = "&7You have successfully kicked &6%s";
    public static String show_not_exist = "&6%s &7does not exist!";
    public static String description_set = "&7You have updated your faction's &6description&7.";
    public static String perm_role_not_exist = "&7The &6permission &7 or the &6role &7does not exist!";
    public static String perm_role_added_successfully = "&7You have added the permission &6%s &7to &6%s&7!";
    public static String perm_role_removed_successfully = "&7You have removed the permission &6%s &7from &6%s&7!";
    public static String ally_self_fail = "&7You can't ally &6yourself&7!";
    public static String ally_already = "&7Your faction is already an ally with &6%s&7!";
    public static String ally_sent_successfully = "&7Successfully send an ally request to &6%s&7!";
    public static String ally_request = "&6%s &7wants to be an ally!";
    public static String ally_remove_successfully = "&6%s &7is not ally anymore!";
    public static String ally_remove_not = "&7Your faction is not allied with &6%s&7!";
    public static String ally_remove_successfully_player = "&7Successfully de-allied with &6%s&7!";
    public static String faction_join_broadcast = "&6%s &7has joined the faction!";
    public static String faction_join_player = "&7You have successfully joined &6%s&7!";
    public static String faction_closed = "&6%s &7 is closed!";
    public static String faction_open_true = "&6Opened &7the faction!";
    public static String faction_open_false = "&6Closed &7the faction!";
    public static String chat_enter = "&7Entered the &6%s &7chat!";
    public static String home_set = "&7Home set on &6%s&7 ; &6%s&7 ; &6%s&7!";
    public static String home_set_failed = "&7You can only set home in &6claimed &7chunks of the faction!";
    public static String home_teleport = "&7Teleported to &6home&7!";
    public static String home_not_set = "&7Your faction &6does not &7have a home set!";
    public static String leave_successfully = "&7You have left &6%s&7!";
    public static String leave_broadcast = "&6%s &7has left the faction!";
    public static String leave_leader = "&7If you want to leave the faction, you need to &6disband &7the faction!";
    public static String fly_enable = "&7Faction fly has been &6enabled&7!";
    public static String fly_disable = "&7Faction fly has been &6disabled&7!";
    public static String fly_force_disable = "&7Flying has been disabled since you have &6left &7your faction land!";
    public static String fly_not_allowed = "&7You can only fly in &6your &7territory!";
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
    private final transient Message instance = this;


//
//    public final void save(Persist persist, File file){
//        persist.save(false, instance, file);
//    }
//
//    public final void load(Persist persist, File file){
//        persist.load(Message.class, file);
//    }

}
