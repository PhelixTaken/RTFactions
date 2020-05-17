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

import java.io.File;

public final class Config {

    private transient Config instance = this;

    public static int factionClaimPower = 1;
    public static int factionPowerPerPlayer = 10;

    public static int factionNameMinLength = 4;
    public static int factionNameMaxLength = 16;


    public final void save(Persist persist, File file){
        persist.save(false, instance, file);
    }

    public final void load(Persist persist, File file){
        persist.load(Config.class, file);
    }

}
