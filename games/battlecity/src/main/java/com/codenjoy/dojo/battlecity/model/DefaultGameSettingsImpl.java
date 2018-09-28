package com.codenjoy.dojo.battlecity.model;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 - 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.codenjoy.dojo.services.settings.Parameter;
import com.codenjoy.dojo.services.settings.SimpleParameter;

public class DefaultGameSettingsImpl implements GameSettings {
    @Override
    public Parameter<Integer> getInitialPlayerAmmoCount() {
        return parameter(20);
    }

    @Override
    public Parameter<Integer> getInitialAIAmmoCount() {
        return parameter(100);
    }

    @Override
    public Parameter<String> getGameMode() {
        return parameter("classic");
    }

    @Override
    public Parameter<String> getMap() {
        return parameter("default");
    }

    @Override
    public Parameter<Integer> getMinAmmoBonusOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMaxAmmoBonusOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMaxAmmoBonusLifeCycle() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinAmmoBonusLifeCycle() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getAmmoBonusGenerationCycle() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getAmmoQuantityInAmmoBonus() {
        return parameter(5);
    }

    @Override
    public Parameter<Integer> getMinHedgeHogsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMaxHedgeHogsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getTicksToUpdateHedgehogs() {
        return parameter(Integer.MAX_VALUE);
    }

    private static <T> SimpleParameter<T> parameter(T t) {
        return new SimpleParameter<T>(t);
    }

    @Override
    public Parameter<Integer> getMaxHedgehogLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinHedgehogLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getPlayerTicksPerBullet() {
        return parameter(4);
    }

    @Override
    public Parameter<Integer> getAiTicksPerBullet() {
        return parameter(1);
    }

    @Override
    public Parameter<Integer> getInitialPlayerLivesCount() {
        return parameter(1);
    }

    @Override
    public Parameter<Integer> getInitialAILivesCount() {
        return parameter(1);
    }

    @Override
    public Parameter<Integer> getMedKitBonusLifeCycle() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinMedKitBonusOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMaxMedKitBonusOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMinMedKitBonusLifeTime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMaxMedKitBonusLifeTime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinBogsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMaxBogsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getTicksToUpdateBogs() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMaxBogLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinBogLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinSandsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMaxSandsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getTicksToUpdateSands() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMaxSandLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinSandLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinMoatsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getMaxMoatsOnMap() {
        return parameter(0);
    }

    @Override
    public Parameter<Integer> getTicksToUpdateMoats() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMaxMoatLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Integer> getMinMoatLifetime() {
        return parameter(Integer.MAX_VALUE);
    }

    @Override
    public Parameter<Boolean> isScoreRecordingEnabled() {
        return parameter(true);
    }
}
