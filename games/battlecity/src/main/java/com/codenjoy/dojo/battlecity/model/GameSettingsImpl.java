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

import com.codenjoy.dojo.battlecity.model.levels.LevelRegistry;
import com.codenjoy.dojo.battlecity.model.modes.BattlecityGameModes;
import com.codenjoy.dojo.services.settings.Parameter;
import com.codenjoy.dojo.services.settings.Settings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameSettingsImpl implements GameSettings {

    private Parameter<Integer> initialPlayerAmmoCount;
    private Parameter<Integer> initialAIAmmoCount;
    private Parameter<String> gameModeName;
    private Parameter<String> map;
    private Parameter<Integer> minHedgeHogsOnMap;
    private Parameter<Integer> maxHedgeHogsOnMap;
    private Parameter<Integer> ticksToUpdateHedgehogs;
    private Parameter<Integer> maxHedgehogLifetime;
    private Parameter<Integer> minHedgehogLifetime;

    private Parameter<Integer> minAmmoBonusOnMap;
    private Parameter<Integer> maxAmmoBonusOnMap;
    private Parameter<Integer> minAmmoBonusLifeCycle;
    private Parameter<Integer> maxAmmoBonusLifeCycle;
    private Parameter<Integer> ammoBonusGenerationCycle;
    private Parameter<Integer> ammoQuantityInAmmoBonus;
    private Parameter<Integer> playerTicksPerBullet;
    private Parameter<Integer> aiTicksPerBullet;
    private Parameter<Integer> initialPlayerLivesCount;
    private Parameter<Integer> initialAILivesCount;
    private Parameter<Integer> medKitBonusLifeCycle;
    private Parameter<Integer> minMedKitBonusOnMap;
    private Parameter<Integer> maxMedKitBonusOnMap;
    private Parameter<Integer> minMedKitBonusLifeTime;
    private Parameter<Integer> maxMedKitBonusLifeTime;
    private Parameter<Integer> minBogsOnMap;
    private Parameter<Integer> maxBogsOnMap;
    private Parameter<Integer> ticksToUpdateBogs;
    private Parameter<Integer> maxBogLifetime;
    private Parameter<Integer> minBogLifetime;
    private Parameter<Integer> minSandsOnMap;
    private Parameter<Integer> maxSandsOnMap;
    private Parameter<Integer> ticksToUpdateSands;
    private Parameter<Integer> maxSandLifetime;
    private Parameter<Integer> minSandLifetime;
    private Parameter<Integer> minMoatsOnMap;
    private Parameter<Integer> maxMoatsOnMap;
    private Parameter<Integer> ticksToUpdateMoats;
    private Parameter<Integer> maxMoatLifetime;
    private Parameter<Integer> minMoatLifetime;

    public GameSettingsImpl(Settings settings, LevelRegistry levelRegistry) {
        initialPlayerAmmoCount = settings.addEditBox("Initial Player Ammo Count").type(Integer.class).def(10);
        initialAIAmmoCount = settings.addEditBox("Initial AI Ammo Count").type(Integer.class).def(5000);
        gameModeName = settings.addSelect("Game Mode", getGameModes())
                .type(String.class).def(BattlecityGameModes.CLASSIC.getName());

        ticksToUpdateHedgehogs = settings.addEditBox("Ticks to update Hedgehogs").type(Integer.class).def(10);
        minHedgeHogsOnMap = settings.addEditBox("Minimum Hedgehogs on the map ").type(Integer.class).def(5);
        maxHedgeHogsOnMap = settings.addEditBox("Maximum Hedgehogs on the map ").type(Integer.class).def(10);
        minHedgehogLifetime = settings.addEditBox("Minimum Hedgehogs lifetime").type(Integer.class).def(4);
        maxHedgehogLifetime = settings.addEditBox("Maximum Hedgehogs lifetime").type(Integer.class).def(30);

        List<?> maps = levelRegistry.getAvailableMapsNames();
        map = settings.addSelect("Map", (List<Object>)maps)
                .type(String.class)
                .def("warmup_1");

        minAmmoBonusOnMap = settings.addEditBox("Minimum AmmoBonus Bonus On Map").type(Integer.class).def(2);
        maxAmmoBonusOnMap = settings.addEditBox("Maximum AmmoBonus Bonus On Map").type(Integer.class).def(4);
        ammoQuantityInAmmoBonus = settings.addEditBox("Number Of Ammo In Ammo Bonus").type(Integer.class).def(20);
        minAmmoBonusLifeCycle = settings.addEditBox("Minimum Ammo Bonus LifeTime").type(Integer.class).def(30);
        maxAmmoBonusLifeCycle = settings.addEditBox("Maximum Ammo Bonus LifeTime").type(Integer.class).def(30);
        ammoBonusGenerationCycle = settings.addEditBox("Ammo Bonus Generation Life Cycle").type(Integer.class).def(20);

        playerTicksPerBullet = settings.addEditBox("Player Ticks per Bullet").type(Integer.class).def(4);
        aiTicksPerBullet = settings.addEditBox("AI Ticks per Bullet").type(Integer.class).def(1);

        initialPlayerLivesCount = settings.addEditBox("Player Start Lives Count").type(Integer.class).def(1);
        initialAILivesCount = settings.addEditBox("AI Start Lives Count").type(Integer.class).def(1);

        medKitBonusLifeCycle = settings.addEditBox("MedKit Bonus Generation Life Cycle").type(Integer.class).def(20);
        minMedKitBonusOnMap = settings.addEditBox("Minimum MedKit Bonus On Map").type(Integer.class).def(5);
        maxMedKitBonusOnMap = settings.addEditBox("Maximum MedKit Bonus On Map").type(Integer.class).def(10);
        minMedKitBonusLifeTime = settings.addEditBox("Minimum MedKit Bonus LifeTime").type(Integer.class).def(20);
        maxMedKitBonusLifeTime = settings.addEditBox("Maximum MedKit Bonus LifeTime").type(Integer.class).def(30);

        ticksToUpdateBogs = settings.addEditBox("Ticks to update Bogs").type(Integer.class).def(10);
        minBogsOnMap = settings.addEditBox("Minimum Bogs on the map ").type(Integer.class).def(5);
        maxBogsOnMap = settings.addEditBox("Maximum Bogs on the map ").type(Integer.class).def(10);
        minBogLifetime = settings.addEditBox("Minimum Bogs lifetime").type(Integer.class).def(20);
        maxBogLifetime = settings.addEditBox("Maximum Bogs lifetime").type(Integer.class).def(30);

        ticksToUpdateSands = settings.addEditBox("Ticks to update Sands").type(Integer.class).def(10);
        minSandsOnMap = settings.addEditBox("Minimum Sands on the map ").type(Integer.class).def(5);
        maxSandsOnMap = settings.addEditBox("Maximum Sands on the map ").type(Integer.class).def(10);
        minSandLifetime = settings.addEditBox("Minimum Sands lifetime").type(Integer.class).def(20);
        maxSandLifetime = settings.addEditBox("Maximum Sands lifetime").type(Integer.class).def(30);

        ticksToUpdateMoats = settings.addEditBox("Ticks to update Moats").type(Integer.class).def(10);
        minMoatsOnMap = settings.addEditBox("Minimum Moats on the map ").type(Integer.class).def(5);
        maxMoatsOnMap = settings.addEditBox("Maximum Moats on the map ").type(Integer.class).def(10);
        minMoatLifetime = settings.addEditBox("Minimum Moats lifetime").type(Integer.class).def(20);
        maxMoatLifetime = settings.addEditBox("Maximum Moats lifetime").type(Integer.class).def(30);

    }

    private List<Object> getGameModes() {
        return Arrays.stream(BattlecityGameModes.values())
                .map(BattlecityGameModes::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Parameter<Integer> getInitialPlayerAmmoCount() {
        return initialPlayerAmmoCount;
    }

    @Override
    public Parameter<Integer> getInitialAIAmmoCount() {
        return initialAIAmmoCount;
    }

    @Override
    public Parameter<String> getGameMode() {
        return gameModeName;
    }
    @Override
    public Parameter<String> getMap() {
        return map;
    }

    @Override
    public Parameter<Integer> getMinAmmoBonusOnMap() {
        return minAmmoBonusOnMap;
    }

    @Override
    public Parameter<Integer> getMaxAmmoBonusOnMap() {
        return maxAmmoBonusOnMap;
    }

    @Override
    public Parameter<Integer> getAmmoQuantityInAmmoBonus() {
        return ammoQuantityInAmmoBonus;
    }

    @Override
    public Parameter<Integer> getMaxAmmoBonusLifeCycle() {
        return maxAmmoBonusLifeCycle;
    }

    @Override
    public Parameter<Integer> getMinAmmoBonusLifeCycle() {
        return minAmmoBonusLifeCycle;
    }

    @Override
    public Parameter<Integer> getAmmoBonusGenerationCycle() {
        return ammoBonusGenerationCycle;
    }

    @Override
    public Parameter<Integer> getMinHedgeHogsOnMap() {
        return minHedgeHogsOnMap;
    }

    @Override
    public Parameter<Integer> getMaxHedgeHogsOnMap() {
        return maxHedgeHogsOnMap;
    }

    @Override
    public Parameter<Integer> getTicksToUpdateHedgehogs() {
        return ticksToUpdateHedgehogs;
    }

    @Override
    public Parameter<Integer> getMaxHedgehogLifetime() {
        return maxHedgehogLifetime;
    }

    @Override
    public Parameter<Integer> getMinHedgehogLifetime() {
        return minHedgehogLifetime;
    }

    @Override
    public Parameter<Integer> getPlayerTicksPerBullet() {
        return playerTicksPerBullet;
    }

    @Override
    public Parameter<Integer> getAiTicksPerBullet() {
        return aiTicksPerBullet;
    }

    @Override
    public Parameter<Integer> getInitialPlayerLivesCount() {
        return initialPlayerLivesCount;
    }

    @Override
    public Parameter<Integer> getInitialAILivesCount() {
        return initialAILivesCount;
    }

    @Override
    public Parameter<Integer> getMedKitBonusLifeCycle() {
        return medKitBonusLifeCycle;
    }

    @Override
    public Parameter<Integer> getMinMedKitBonusOnMap() {
        return minMedKitBonusOnMap;
    }

    @Override
    public Parameter<Integer> getMaxMedKitBonusOnMap() {
        return maxMedKitBonusOnMap;
    }

    @Override
    public Parameter<Integer> getMinMedKitBonusLifeTime() {
        return minMedKitBonusLifeTime;
    }

    @Override
    public Parameter<Integer> getMaxMedKitBonusLifeTime() {
        return maxMedKitBonusLifeTime;
    }

    @Override
    public Parameter<Integer> getMinBogsOnMap() {
        return minBogsOnMap;
    }

    @Override
    public Parameter<Integer> getMaxBogsOnMap() {
        return maxBogsOnMap;
    }

    @Override
    public Parameter<Integer> getTicksToUpdateBogs() {
        return ticksToUpdateBogs;
    }

    @Override
    public Parameter<Integer> getMaxBogLifetime() {
        return maxBogLifetime;
    }

    @Override
    public Parameter<Integer> getMinBogLifetime() {
        return minBogLifetime;
    }

    @Override
    public Parameter<Integer> getMinSandsOnMap() {
        return minSandsOnMap;
    }

    @Override
    public Parameter<Integer> getMaxSandsOnMap() {
        return maxSandsOnMap;
    }

    @Override
    public Parameter<Integer> getTicksToUpdateSands() {
        return ticksToUpdateSands;
    }

    @Override
    public Parameter<Integer> getMaxSandLifetime() {
        return maxSandLifetime;
    }

    @Override
    public Parameter<Integer> getMinSandLifetime() {
        return minSandLifetime;
    }

    @Override
    public Parameter<Integer> getMinMoatsOnMap() {
        return minMoatsOnMap;
    }

    @Override
    public Parameter<Integer> getMaxMoatsOnMap() {
        return maxMoatsOnMap;
    }

    @Override
    public Parameter<Integer> getTicksToUpdateMoats() {
        return ticksToUpdateMoats;
    }

    @Override
    public Parameter<Integer> getMaxMoatLifetime() {
        return maxMoatLifetime;
    }

    @Override
    public Parameter<Integer> getMinMoatLifetime() {
        return minMoatLifetime;
    }
}
