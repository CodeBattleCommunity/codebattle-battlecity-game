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

import java.util.List;

public class MedKitBonusController implements Tickable {

    private Field field;
    private Dice dice;

    private List<MedKitBonus> elements;
    private int tick;
    private Parameter<Integer> ticksToUpdateGeneration;
    private final Parameter<Integer> minElementsOnMap;
    private final Parameter<Integer> maxElementsOnMap;
    private final Parameter<Integer> minLifeTime;
    private final Parameter<Integer> maxLifeTime;

    public MedKitBonusController(Field field, GameSettings settings, Dice dice) {
        this.field = field;
        this.dice = dice;
        tick = 0;

        ticksToUpdateGeneration = settings.getMedKitBonusGenerationCycle();
        minElementsOnMap = settings.getMinMedKitBonusOnMap();
        maxElementsOnMap = settings.getMaxMedKitBonusOnMap();
        minLifeTime = settings.getMinMedKitBonusLifeTime();
        maxLifeTime = settings.getMaxMedKitBonusLifeTime();

        elements = field.getMedKitBonuses();
    }

    @Override
    public void tick() {
        elements.forEach(MedKitBonus::tick);
        removeDeadMedKitBonuses();
        createNewHMedKitBonuses();
    }

    private void createNewHMedKitBonuses() {
        LengthToXY xy = new LengthToXY(field.size());

        final int numberOfElementsForCreation = howManyElementsToCreate();
        boolean fieldOccupied;
        int coverage = field.size() * field.size();

        int createdElements = 0;

        if (tick >= ticksToUpdateGeneration.getValue()) {
            while (createdElements <= numberOfElementsForCreation) {
                int index = dice.next(coverage);
                int lifeCount = getLifeTime();

                fieldOccupied = field.isFieldOccupied(xy.getXY(index).getX(), xy.getXY(index).getY());
                if (!fieldOccupied) {
                    elements.add(new MedKitBonus(xy.getXY(index), lifeCount));
                    createdElements++;
                }
            }
            tick = 0;
        } else {
            tick++;
        }

    }

    private int howManyElementsToCreate() {
        return minElementsOnMap.getValue()
                + dice.next(maxElementsOnMap.getValue() - minElementsOnMap.getValue());
    }

    private int getLifeTime() {
        return minLifeTime.getValue()
                + dice.next(maxLifeTime.getValue() - minLifeTime.getValue());
    }

    private void removeDeadMedKitBonuses() {
        elements.removeIf(h -> !h.isAlive());
    }
}
