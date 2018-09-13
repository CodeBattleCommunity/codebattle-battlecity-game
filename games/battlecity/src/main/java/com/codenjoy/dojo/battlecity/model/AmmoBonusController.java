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

import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.LengthToXY;
import com.codenjoy.dojo.services.Tickable;
import com.codenjoy.dojo.services.settings.Parameter;

public class AmmoBonusController implements Tickable {

    private Field field;
    private final Parameter<Integer> maxAmmoBonusOnMap;
    private final Parameter<Integer> minLifeTime;
    private Dice dice;
    private final Parameter<Integer> maxLifeTime;
    private final Parameter<Integer> ammoQuantityInAmmoBonus;
    private int tick = 0;
    private Parameter<Integer> ticksToUpdateGeneration;


    public AmmoBonusController(Field field, GameSettings gameSettings, Dice dice) {
        this.field = field;
        this.dice = dice;

        maxLifeTime = gameSettings.getMaxAmmoBonusLifeCycle();
        minLifeTime = gameSettings.getMinAmmoBonusLifeCycle();
        maxAmmoBonusOnMap = gameSettings.getAmmoBonusCountOnMap();
        ammoQuantityInAmmoBonus = gameSettings.getAmmoQuantityInAmmoBonus();
        ticksToUpdateGeneration = gameSettings.getAmmoBonusGenerationCycle();
    }

    @Override
    public void tick() {
        field.getAmmoBonuses().forEach(AmmoBonus::tick);
        removePickedAmmoBonus();
        createNewAmmoBonus();
    }

    private void createNewAmmoBonus() {
        if (tick >= ticksToUpdateGeneration.getValue()) {
            LengthToXY xy = new LengthToXY(field.size());
            final int numberOfAmmoBonusForCreation = maxAmmoBonusOnMap.getValue() - field.getAmmoBonuses().size();

            boolean fieldOccupied;
            int coverage = field.size() * field.size();

            int createdElements = 0;

            while (createdElements < numberOfAmmoBonusForCreation) {
                int index = dice.next(coverage);
                int lifeCycleRandom = getLifeTime();

                fieldOccupied = field.isFieldOccupied(xy.getXY(index).getX(), xy.getXY(index).getY());

                if (!fieldOccupied) {
                    field.getAmmoBonuses().add(new AmmoBonus(xy.getXY(index), lifeCycleRandom, ammoQuantityInAmmoBonus.getValue()));
                    createdElements++;
                }
            }
            tick = 0;
        } else {
            tick++;
        }
    }

    private void removePickedAmmoBonus() {
        field.getAmmoBonuses().removeIf(ammoBonus -> !ammoBonus.isAlive());
    }

    private int getLifeTime() {
        return minLifeTime.getValue()
                + dice.next(maxLifeTime.getValue() - minLifeTime.getValue());
    }


}
