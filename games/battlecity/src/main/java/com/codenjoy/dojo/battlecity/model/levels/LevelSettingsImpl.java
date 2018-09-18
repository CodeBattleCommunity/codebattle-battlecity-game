package com.codenjoy.dojo.battlecity.model.levels;

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

import java.util.Optional;

public class LevelSettingsImpl implements LevelSettings {

    private String gameMode;
    private Integer initialPlayerAmmoCount;
    private Integer initialAIAmmoCount;
    private Integer ammoBonusCountOnMap;
    private Integer minAmmoBonusLifeCycle;
    private Integer maxAmmoBonusLifeCycle;
    private Integer ammoBonusGenerationCycle;
    private Integer ammoQuantityInAmmoBonus;
    private Integer minHedgeHogsOnMap;
    private Integer maxHedgeHogsOnMap;
    private Integer ticksToUpdateHedgehogs;
    private Integer maxHedgehogLifetime;
    private Integer minHedgehogLifetime;
    private Integer gameSpeed;
    private Integer playerTicksPerBullet;
    private Integer aiTicksPerBullet;
    private Integer initialPlayerLivesCount;
    private Integer initialAILivesCount;
    private Integer medKitBonusLifeCycle;
    private Integer minMedKitBonusOnMap;
    private Integer maxMedKitBonusOnMap;
    private Integer minMedKitBonusLifeTime;
    private Integer maxMedKitBonusLifeTime;
    private Integer minBogsOnMap;
    private Integer maxBogsOnMap;
    private Integer minBogLifetime;
    private Integer maxBogLifetime;
    private Integer ticksToUpdateBogs;
    private Integer minSandsOnMap;
    private Integer maxSandsOnMap;
    private Integer minSandLifetime;
    private Integer maxSandLifetime;
    private Integer ticksToUpdateSands;
    private Integer minMoatsOnMap;
    private Integer maxMoatsOnMap;
    private Integer minMoatLifetime;
    private Integer maxMoatLifetime;
    private Integer ticksToUpdateMoats;

    @Override
    public Optional<String> getGameMode() {
        return Optional.ofNullable(gameMode);
    }

    @Override
    public Optional<Integer> getInitialPlayerAmmoCount() {
        return Optional.ofNullable(initialPlayerAmmoCount);
    }

    @Override
    public Optional<Integer> getInitialAIAmmoCount() {
        return Optional.ofNullable(initialAIAmmoCount);
    }

    @Override
    public Optional<Integer> getAmmoBonusCountOnMap() {
        return Optional.ofNullable(ammoBonusCountOnMap);
    }

    @Override
    public Optional<Integer> getMaxAmmoBonusLifeCycle() {
        return Optional.ofNullable(maxAmmoBonusLifeCycle);
    }

    @Override
    public Optional<Integer> getMinAmmoBonusLifeCycle() {
        return Optional.ofNullable(minAmmoBonusLifeCycle);
    }

    @Override
    public Optional<Integer> getAmmoBonusGenerationCycle() {
        return Optional.ofNullable(ammoBonusGenerationCycle);
    }

    @Override
    public Optional<Integer> getAmmoQuantityInAmmoBonus() {
        return Optional.ofNullable(ammoQuantityInAmmoBonus);
    }

    @Override
    public Optional<Integer> getMinHedgeHogsOnMap() {
        return Optional.ofNullable(minHedgeHogsOnMap);
    }

    @Override
    public Optional<Integer> getMaxHedgeHogsOnMap() {
        return Optional.ofNullable(maxHedgeHogsOnMap);
    }

    @Override
    public Optional<Integer> getTicksToUpdateHedgehogs() {
        return Optional.ofNullable(ticksToUpdateHedgehogs);
    }

    @Override
    public Optional<Integer> getMaxHedgehogLifetime() {
        return Optional.ofNullable(maxHedgehogLifetime);
    }

    @Override
    public Optional<Integer> getMinHedgehogLifetime() {
        return Optional.ofNullable(minHedgehogLifetime);
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setInitialPlayerAmmoCount(Integer initialPlayerAmmoCount) {
        this.initialPlayerAmmoCount = initialPlayerAmmoCount;
    }

    public void setInitialAIAmmoCount(Integer initialAIAmmoCount) {
        this.initialAIAmmoCount = initialAIAmmoCount;
    }

    public void setAmmoBonusCountOnMap(Integer ammoBonusCountOnMap) {
        this.ammoBonusCountOnMap = ammoBonusCountOnMap;
    }

    public void setAmmoBonusLifeCycle(Integer ammoBonusLifeCycle) {
    }

    public void setAmmoQuantityInAmmoBonus(Integer ammoQuantityInAmmoBonus) {
        this.ammoQuantityInAmmoBonus = ammoQuantityInAmmoBonus;
    }

    public void setMinHedgeHogsOnMap(Integer minHedgeHogsOnMap) {
        this.minHedgeHogsOnMap = minHedgeHogsOnMap;
    }

    public void setMaxHedgeHogsOnMap(Integer maxHedgeHogsOnMap) {
        this.maxHedgeHogsOnMap = maxHedgeHogsOnMap;
    }

    public void setTicksToUpdateHedgehogs(Integer ticksToUpdateHedgehogs) {
        this.ticksToUpdateHedgehogs = ticksToUpdateHedgehogs;
    }

    public void setMaxHedgehogLifetime(Integer maxHedgehogLifetime) {
        this.maxHedgehogLifetime = maxHedgehogLifetime;
    }

    public void setMinHedgehogLifetime(Integer minHedgehogLifetime) {
        this.minHedgehogLifetime = minHedgehogLifetime;
    }

    @Override
    public Optional<Integer> getGameSpeed() {
        return Optional.ofNullable(gameSpeed);
    }

    public void setGameSpeed(Integer gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    @Override
    public Optional<Integer> getPlayerTicksPerBullet() {
        return Optional.ofNullable(playerTicksPerBullet);
    }

    @Override
    public Optional<Integer> getAiTicksPerBullet() {
        return Optional.ofNullable(aiTicksPerBullet);
    }

    public void setPlayerTicksPerBullet(Integer playerTicksPerBullet) {
        this.playerTicksPerBullet = playerTicksPerBullet;
    }

    public void setAiTicksPerBullet(Integer aiTicksPerBullet) {
        this.aiTicksPerBullet = aiTicksPerBullet;
    }

    @Override
    public Optional<Integer> getInitialPlayerLivesCount() {
        return Optional.ofNullable(initialPlayerLivesCount);
    }

    @Override
    public Optional<Integer> getInitialAILivesCount() {
        return Optional.ofNullable(initialAILivesCount);
    }

    public void setInitialPlayerLivesCount(Integer initialPlayerLivesCount) {
        this.initialPlayerLivesCount = initialPlayerLivesCount;
    }

    public void setInitialAILivesCount(Integer initialAILivesCount) {
        this.initialAILivesCount = initialAILivesCount;
    }

    @Override
    public Optional<Integer> getMedKitBonusLifeCycle() {
        return Optional.ofNullable(medKitBonusLifeCycle);
    }

    @Override
    public Optional<Integer> getMinMedKitBonusOnMap() {
        return Optional.ofNullable(minMedKitBonusOnMap);
    }

    @Override
    public Optional<Integer> getMaxMedKitBonusOnMap() {
        return Optional.ofNullable(maxMedKitBonusOnMap);
    }

    @Override
    public Optional<Integer> getMinBogsOnMap() {
        return Optional.ofNullable(minBogsOnMap);
    }

    @Override
    public Optional<Integer> getMaxBogsOnMap() {
        return Optional.ofNullable(maxBogsOnMap);
    }

    @Override
    public Optional<Integer> getMinBogLifetime() {
        return Optional.ofNullable(minBogLifetime);
    }

    @Override
    public Optional<Integer> getMaxBogLifetime() {
        return Optional.ofNullable(maxBogLifetime);
    }

    @Override
    public Optional<Integer> getTicksToUpdateBogs() {
        return Optional.ofNullable(ticksToUpdateBogs);
    }

    @Override
    public Optional<Integer> getMinSandsOnMap() {
        return Optional.ofNullable(minSandsOnMap);
    }

    @Override
    public Optional<Integer> getMaxSandsOnMap() {
        return Optional.ofNullable(maxSandsOnMap);
    }

    @Override
    public Optional<Integer> getMinSandLifetime() {
        return Optional.ofNullable(minSandLifetime);
    }

    @Override
    public Optional<Integer> getMaxSandLifetime() {
        return Optional.ofNullable(maxSandLifetime);
    }

    @Override
    public Optional<Integer> getTicksToUpdateSands() {
        return Optional.ofNullable(ticksToUpdateSands);
    }

    @Override
    public Optional<Integer> getMinMoatsOnMap() {
        return Optional.ofNullable(minMoatsOnMap);
    }

    @Override
    public Optional<Integer> getMaxMoatsOnMap() {
        return Optional.ofNullable(maxMoatsOnMap);
    }

    @Override
    public Optional<Integer> getMinMoatLifetime() {
        return Optional.ofNullable(minMoatLifetime);
    }

    @Override
    public Optional<Integer> getMaxMoatLifetime() {
        return Optional.ofNullable(maxMoatLifetime);
    }

    @Override
    public Optional<Integer> getTicksToUpdateMoats() {
        return Optional.ofNullable(ticksToUpdateMoats);
    }

    public void setMedKitBonusLifeCycle(Integer medKitBonusLifeCycle) {
        this.medKitBonusLifeCycle = medKitBonusLifeCycle;
    }

    public void setMinMedKitBonusOnMap(Integer minMedKitBonusOnMap) {
        this.minMedKitBonusOnMap = minMedKitBonusOnMap;
    }

    public void setMaxMedKitBonusOnMap(Integer maxMedKitBonusOnMap) {
        this.maxMedKitBonusOnMap = maxMedKitBonusOnMap;
    }

    @Override
    public Optional<Integer> getMinMedKitBonusLifeTime() {
        return Optional.ofNullable(minMedKitBonusLifeTime);
    }

    @Override
    public Optional<Integer> getMaxMedKitBonusLifeTime() {
        return Optional.ofNullable(maxMedKitBonusLifeTime);
    }

    public void setMinMedKitBonusLifeTime(Integer minMedKitBonusLifeTime) {
        this.minMedKitBonusLifeTime = minMedKitBonusLifeTime;
    }

    public void setMaxMedKitBonusLifeTime(Integer maxMedKitBonusLifeTime) {
        this.maxMedKitBonusLifeTime = maxMedKitBonusLifeTime;
    }

    public void setMinAmmoBonusLifeCycle(Integer minAmmoBonusLifeCycle) {
        this.minAmmoBonusLifeCycle = minAmmoBonusLifeCycle;
    }

    public void setMaxAmmoBonusLifeCycle(Integer maxAmmoBonusLifeCycle) {
        this.maxAmmoBonusLifeCycle = maxAmmoBonusLifeCycle;
    }

    public void setAmmoBonusGenerationCycle(Integer ammoBonusGenerationCycle) {
        this.ammoBonusGenerationCycle = ammoBonusGenerationCycle;
    }

    public void setMaxBogsOnMap(Integer maxBogsOnMap) {
        this.maxBogsOnMap = maxBogsOnMap;
    }

    public void setMinBogsOnMap(Integer minBogsOnMap) {
        this.minBogsOnMap = minBogsOnMap;
    }

    public void setMinBogLifetime(Integer minBogLifetime) {
        this.minBogLifetime = minBogLifetime;
    }

    public void setMaxBogLifetime(Integer maxBogLifetime) {
        this.maxBogLifetime = maxBogLifetime;
    }

    public void setTicksToUpdateBogs(Integer ticksToUpdateBogs) {
        this.ticksToUpdateBogs = ticksToUpdateBogs;
    }

    public void setMaxSandsOnMap(Integer maxSandsOnMap) {
        this.maxSandsOnMap = maxSandsOnMap;
    }

    public void setMinSandsOnMap(Integer minSandsOnMap) {
        this.minSandsOnMap = minSandsOnMap;
    }

    public void setMinSandLifetime(Integer minSandLifetime) {
        this.minSandLifetime = minSandLifetime;
    }

    public void setMaxSandLifetime(Integer maxSandLifetime) {
        this.maxSandLifetime = maxSandLifetime;
    }

    public void setTicksToUpdateSands(Integer ticksToUpdateSands) {
        this.ticksToUpdateSands = ticksToUpdateSands;
    }

    public void setMaxMoatsOnMap(Integer maxMoatsOnMap) {
        this.maxMoatsOnMap = maxMoatsOnMap;
    }

    public void setMinMoatsOnMap(Integer minMoatsOnMap) {
        this.minMoatsOnMap = minMoatsOnMap;
    }

    public void setMinMoatLifetime(Integer minMoatLifetime) {
        this.minMoatLifetime = minMoatLifetime;
    }

    public void setMaxMoatLifetime(Integer maxMoatLifetime) {
        this.maxMoatLifetime = maxMoatLifetime;
    }

    public void setTicksToUpdateMoats(Integer ticksToUpdateMoats) {
        this.ticksToUpdateMoats = ticksToUpdateMoats;
    }
}
