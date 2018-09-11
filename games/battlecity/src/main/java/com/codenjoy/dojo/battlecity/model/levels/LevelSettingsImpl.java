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
    private Integer ammoBonusLifeCycle;
    private Integer ammoQuantityInAmmoBonus;
    private Integer maxHedgeHogsOnMap;
    private Integer ticksToUpdateHedgehogs;
    private Integer maxHedgehogLifetime;
    private Integer minHedgehogLifetime;

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
    public Optional<Integer> getAmmoBonusLifeCycle() {
        return Optional.ofNullable(ammoBonusLifeCycle);
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

    public void setInitialPlayerAmmoCount(int initialPlayerAmmoCount) {
        this.initialPlayerAmmoCount = initialPlayerAmmoCount;
    }

    public void setInitialAIAmmoCount(int initialAIAmmoCount) {
        this.initialAIAmmoCount = initialAIAmmoCount;
    }

    public void setAmmoBonusCountOnMap(int ammoBonusCountOnMap) {
        this.ammoBonusCountOnMap = ammoBonusCountOnMap;
    }

    public void setAmmoBonusLifeCycle(int ammoBonusLifeCycle) {
        this.ammoBonusLifeCycle = ammoBonusLifeCycle;
    }

    public void setAmmoQuantityInAmmoBonus(int ammoQuantityInAmmoBonus) {
        this.ammoQuantityInAmmoBonus = ammoQuantityInAmmoBonus;
    }

    public void setMaxHedgeHogsOnMap(int maxHedgeHogsOnMap) {
        this.maxHedgeHogsOnMap = maxHedgeHogsOnMap;
    }

    public void setTicksToUpdateHedgehogs(int ticksToUpdateHedgehogs) {
        this.ticksToUpdateHedgehogs = ticksToUpdateHedgehogs;
    }

    public void setMaxHedgehogLifetime(int maxHedgehogLifetime) {
        this.maxHedgehogLifetime = maxHedgehogLifetime;
    }

    public void setMinHedgehogLifetime(int minHedgehogLifetime) {
        this.minHedgehogLifetime = minHedgehogLifetime;
    }
}
