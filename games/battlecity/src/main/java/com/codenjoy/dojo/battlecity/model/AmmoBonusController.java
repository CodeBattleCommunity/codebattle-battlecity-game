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

import java.util.Random;

public class AmmoBonusController {


    private static final int MIN_LIFECYCLE = 10;
    private Field field;
    private final Parameter<Integer> maxAmmoBonusOnMap;
    private final Parameter<Integer> ammoBonusLifeCycle;
    private final Parameter<Integer> ammoQuantityInAmmoBonus;
    private Random random = new Random();


    public AmmoBonusController(Field field, GameSettings gameSettings) {
        this.field = field;

        maxAmmoBonusOnMap = gameSettings.getAmmoBonusCountOnMap();
        ammoBonusLifeCycle = gameSettings.getAmmoBonusLifeCycle();
        ammoQuantityInAmmoBonus = gameSettings.getAmmoQuantityInAmmoBonus();

    }

    private void createNewAmmoBonus() {
        LengthToXY xy = new LengthToXY(field.size());
        final int numberOfAmmoBonusForCreation = maxAmmoBonusOnMap.getValue() - field.getAmmoBonuses().size();
        int currentAmmoBonusCount = 0;
        int lifeCycleRandom = MIN_LIFECYCLE;
        boolean fieldOccupied;
        int coverage = field.size() * field.size();

        while (currentAmmoBonusCount < numberOfAmmoBonusForCreation) {
            int index = random.nextInt(coverage);
            lifeCycleRandom += random.nextInt(ammoBonusLifeCycle.getValue());
            fieldOccupied = field.isFieldOccupied(xy.getXY(index).getX(), xy.getXY(index).getY());
            if (!fieldOccupied) {
                field.getAmmoBonuses().add(new AmmoBonus(xy.getXY(index), lifeCycleRandom,ammoQuantityInAmmoBonus.getValue()));
                currentAmmoBonusCount++;
            }
        }
    }

    private void removePickedAmmoBonus() {
        field.getAmmoBonuses().removeIf(ammoBonus -> !ammoBonus.isAlive());
    }

    public void refreshAmmoBonus() {
        removePickedAmmoBonus();
        createNewAmmoBonus();

    }

    public void tick() {
        field.getAmmoBonuses().forEach(AmmoBonus::tick);
    }
}
