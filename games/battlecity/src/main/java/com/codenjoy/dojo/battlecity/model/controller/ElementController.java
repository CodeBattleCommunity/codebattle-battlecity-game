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
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.Tickable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public abstract class ElementController<T extends ManagedElement & Point> implements Tickable {

    private final Field fieldController;
    private GameSettings gameSettings;
    private List<T> elements;
    private Dice dice;

    private int tick;
    private boolean startGame = true;

    private final List<Point> respawnPlaces;
    private CreationStrategy creationStrategy;

    public ElementController(Field fieldController, GameSettings settings, List<T> elements, Dice dice) {
        this.fieldController = fieldController;
        // settings from admin's page

        this.gameSettings = settings;
        this.elements = elements;
        this.dice = dice;

        if (!elements.isEmpty()) {
            respawnPlaces = elements.stream()
                    .map(e -> new PointImpl(e.getX(), e.getY()))
                    .collect(Collectors.toList());

            creationStrategy = new FixedRespawnPlacesCreationStrategy();
        } else {
            respawnPlaces = Collections.emptyList();
            creationStrategy = new AllFieldsCreationStrategy();
        }
    }

    private void createNewElements() {
        ElementControllerSettings elementSettings = getElementSettings(gameSettings);

        int ticksToUpdate = elementSettings.getTicksToUpdate().getValue();
        int maxElementsOnMap = elementSettings.getMaxElementsOnMap().getValue();
        int minElementsOnMap = elementSettings.getMinElementsOnMap().getValue();
        int maxElementLifetime = elementSettings.getMaxElementLifetime().getValue();
        int minElementLifetime = elementSettings.getMinElementLifetime().getValue();

        minElementsOnMap = Math.min(minElementsOnMap, maxElementsOnMap);
        minElementLifetime = Math.min(minElementLifetime, maxElementLifetime);

        if (!hasCorrectSettings(maxElementLifetime, maxElementsOnMap)) {
            return;
        }

        LengthToXY xy = new LengthToXY(fieldController.size());
        final int numberOfElementsForCreation = creationStrategy.howManyElementsToCreate(minElementsOnMap, maxElementsOnMap);

        // to prevent infinite loop
        int maxIterations = getCoverage() / 2;
        int cycles = 0;

        if (tick >= ticksToUpdate - 1 || startGame) {
            while (elements.size() < numberOfElementsForCreation && cycles < maxIterations) {
                int lifeCount = randomElementsCount(minElementLifetime, maxElementLifetime);

                Point point = creationStrategy.findPlaceForElement(xy, getCoverage());

                boolean fieldOccupied = fieldController.isFieldOccupied(point.getX(), point.getY());
                if (!fieldOccupied) {
                    elements.add(createNewElement(point, lifeCount));
                }
                cycles++;
            }
            tick = 0;

            startGame = false;
        } else {
            tick++;
        }
    }

    private int getCoverage() {
        return fieldController.size() * fieldController.size();
    }

    protected abstract T createNewElement(Point xy, int lifeCount);

    protected abstract ElementControllerSettings getElementSettings(GameSettings gameSettings);

    private boolean hasCorrectSettings(int maxElementLifetime, int maxElementsOnMap) {
        return maxElementLifetime > 0 && maxElementsOnMap > 0;
    }

    private int randomElementsCount(int minElementsOnMap, int maxElementsOnMap) {
        if (maxElementsOnMap <= 0) {
            return 0;
        }

        if (minElementsOnMap == maxElementsOnMap) {
            return minElementsOnMap;
        } else return minElementsOnMap
                + dice.next(maxElementsOnMap - minElementsOnMap);
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

    private interface CreationStrategy {
        int howManyElementsToCreate(int minElementsOnMap, int maxElementsOnMap);

        Point findPlaceForElement(LengthToXY xy, int coverage);
    }

    private class AllFieldsCreationStrategy implements CreationStrategy {
        @Override
        public int howManyElementsToCreate(int minElementsOnMap, int maxElementsOnMap) {
            return randomElementsCount(minElementsOnMap, maxElementsOnMap) - elements.size();
        }

        @Override
        public Point findPlaceForElement(LengthToXY xy, int coverage) {
            int index = dice.next(coverage);
            return xy.getXY(index);
        }
    }

    private class FixedRespawnPlacesCreationStrategy implements CreationStrategy {
        @Override
        public int howManyElementsToCreate(int minElementsOnMap, int maxElementsOnMap) {
            return randomElementsCount(minElementsOnMap, maxElementsOnMap) - elements.size();
        }

        @Override
        public Point findPlaceForElement(LengthToXY xy, int coverage) {
            List<Point> freeRespawnPlaces = getFreeRespawnPlaces();

            if (!freeRespawnPlaces.isEmpty()) {
                return freeRespawnPlaces.get(dice.next(freeRespawnPlaces.size()));
            } else {
                return xy.getXY(dice.next(coverage));
            }
        }

        private List<Point> getFreeRespawnPlaces() {
            return respawnPlaces.stream()
                    .filter(p -> !fieldController.isFieldOccupied(p.getX(), p.getY()))
                    .collect(Collectors.toList());
        }
    }
}
