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
}
