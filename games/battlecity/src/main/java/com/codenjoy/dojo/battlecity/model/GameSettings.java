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

public interface GameSettings {
    Parameter<Integer> getInitialPlayerAmmoCount();
    Parameter<Integer> getInitialAIAmmoCount();
    Parameter<String> getGameMode();
    Parameter<String> getMap();
    Parameter<Integer> getMinAmmoBonusOnMap();
    Parameter<Integer> getMaxAmmoBonusOnMap();
    Parameter<Integer> getMaxAmmoBonusLifeCycle();
    Parameter<Integer> getMinAmmoBonusLifeCycle();
    Parameter<Integer> getAmmoBonusGenerationCycle();
    Parameter<Integer> getAmmoQuantityInAmmoBonus();
    Parameter<Integer> getMinHedgeHogsOnMap();
    Parameter<Integer> getMaxHedgeHogsOnMap();
    Parameter<Integer> getTicksToUpdateHedgehogs();
    Parameter<Integer> getMaxHedgehogLifetime();
    Parameter<Integer> getMinHedgehogLifetime();
    Parameter<Integer> getPlayerTicksPerBullet();
    Parameter<Integer> getAiTicksPerBullet();
    Parameter<Integer> getInitialPlayerLivesCount();
    Parameter<Integer> getInitialAILivesCount();

    Parameter<Integer> getMedKitBonusGenerationCycle();
    Parameter<Integer> getMinMedKitBonusOnMap();
    Parameter<Integer> getMaxMedKitBonusOnMap();
    Parameter<Integer> getMinMedKitBonusLifeTime();
    Parameter<Integer> getMaxMedKitBonusLifeTime();
    Parameter<Integer> getMinBogsOnMap();
    Parameter<Integer> getMaxBogsOnMap();
    Parameter<Integer> getTicksToUpdateBogs();
    Parameter<Integer> getMaxBogLifetime();
    Parameter<Integer> getMinBogLifetime();
    Parameter<Integer> getMinSandsOnMap();
    Parameter<Integer> getMaxSandsOnMap();
    Parameter<Integer> getTicksToUpdateSands();
    Parameter<Integer> getMaxSandLifetime();
    Parameter<Integer> getMinSandLifetime();
    Parameter<Integer> getMinMoatsOnMap();
    Parameter<Integer> getMaxMoatsOnMap();
    Parameter<Integer> getTicksToUpdateMoats();
    Parameter<Integer> getMaxMoatLifetime();
    Parameter<Integer> getMinMoatLifetime();

    Parameter<Boolean> isScoreRecordingEnabled();
}
