package com.codenjoy.dojo.battlecity.model.controller;

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


import com.codenjoy.dojo.battlecity.model.Field;
import com.codenjoy.dojo.battlecity.model.GameSettings;
import com.codenjoy.dojo.battlecity.model.ManagedElement;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.LengthToXY;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.Tickable;
import com.codenjoy.dojo.services.settings.Parameter;

import java.util.List;


public abstract class ElementController<T extends ManagedElement> implements Tickable {

    private final Field fieldController;
    private GameSettings gameSettings;
    private List<T> elements;
    private Dice dice;

    private int tick;
    private boolean startGame = true;

    public ElementController(Field fieldController, GameSettings settings, List<T> elements, Dice dice) {
        this.fieldController = fieldController;
        // settings from admin's page

        this.gameSettings = settings;
        this.elements = elements;
        this.dice = dice;
    }

    private void createNewElements() {
        ElementControllerSettings elementSettings = getElementSettings(gameSettings);

        Parameter<Integer> ticksToUpdate = elementSettings.getTicksToUpdate();
        Parameter<Integer> maxElementsOnMap = elementSettings.getMaxElementsOnMap();
        Parameter<Integer> maxElementLifetime = elementSettings.getMaxElementLifetime();
        Parameter<Integer> minElementLifetime = elementSettings.getMinElementLifetime();

        if (!hasCorrectSettings(maxElementLifetime.getValue(), maxElementsOnMap.getValue())) {
            return;
        }

        LengthToXY xy = new LengthToXY(fieldController.size());
        final int numberOfElementsForCreation =
                maxElementsOnMap.getValue() - elements.size();
        int currentElementCount = 0;
        int lifeCount = 0;
        boolean fieldOccupied;
        int coverage = fieldController.size() * fieldController.size();

        if (tick >= ticksToUpdate.getValue() || startGame) {
            while (currentElementCount < numberOfElementsForCreation) {

                int index = dice.next(coverage);
                lifeCount = minElementLifetime.getValue() + dice.next(maxElementLifetime.getValue());
                fieldOccupied = fieldController.isFieldOccupied(xy.getXY(index).getX(), xy.getXY(index).getY());
                if (!fieldOccupied) {
                    elements.add(createNewElement(xy.getXY(index), lifeCount));
                    currentElementCount++;
                }
            }
            tick = 0;
            startGame = false;
        } else {
            tick++;
        }
    }

    protected abstract T createNewElement(Point xy, int lifeCount);

    protected abstract ElementControllerSettings getElementSettings(GameSettings gameSettings);

    private boolean hasCorrectSettings(int maxElementLifetime, int maxElementsOnMap) {
        return maxElementLifetime > 0 && maxElementsOnMap > 0;
    }

    private void removeDeadElements() {
        elements.removeIf(h -> !h.isAlive());
    }

    @Override
    public void tick() {
        elements.forEach(Tickable::tick);
        removeDeadElements();
        createNewElements();
    }
}
