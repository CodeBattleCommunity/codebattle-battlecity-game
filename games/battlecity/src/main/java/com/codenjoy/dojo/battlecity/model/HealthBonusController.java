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


import com.codenjoy.dojo.services.LengthToXY;
import com.codenjoy.dojo.services.settings.Parameter;

import java.util.List;
import java.util.Random;

public class HealthBonusController {

    private Field field;
    private Parameter<Integer> healthBonusLifeCycle;
    private Parameter<Integer> healthBonusOnMap;
    private Random random = new Random();

    public HealthBonusController(Field field, GameSettings gameSettings) {
        this.field = field;
        healthBonusLifeCycle = gameSettings.getHealthBonusLifeCycle();
        healthBonusOnMap = gameSettings.getHealthBonusOnMap();
    }

    private void createNewHealthBonus() {
        LengthToXY xy = new LengthToXY(field.size());
        final int numberOfHealthBonusForCreation = healthBonusOnMap.getValue() - field.getHealthBonuses().size();
        int currentHealthBonusCount = 0;
        int lifeCycle = 0;
        boolean fieldOccupied;
        int coverage = field.size() * field.size();
        while (currentHealthBonusCount < numberOfHealthBonusForCreation) {
            int index = random.nextInt(coverage);
            lifeCycle += random.nextInt(healthBonusLifeCycle.getValue());
            fieldOccupied = field.isFieldOccupied(xy.getXY(index).getX(), xy.getXY(index).getY());
            if (!fieldOccupied) {
                field.getHealthBonuses().add(new HealthBonus(xy.getXY(index), lifeCycle));
                currentHealthBonusCount++;
            }
        }
    }

    private void removeHealthBonus() {
        field.getHealthBonuses().removeIf(healthBonus -> !healthBonus.isAlive());
    }

    public void refreshHealthBonus() {
        tick();
        removeHealthBonus();
        createNewHealthBonus();
    }

    public void tick() {
        field.getHealthBonuses().forEach(HealthBonus::tick);
    }
}

