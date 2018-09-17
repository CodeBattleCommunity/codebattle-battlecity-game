package com.codenjoy.dojo.battlecity.model.obstacle;

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
import com.codenjoy.dojo.services.LengthToXY;
import com.codenjoy.dojo.services.Tickable;
import com.codenjoy.dojo.services.settings.Parameter;

import java.util.List;
import java.util.Random;

public class BogController implements Tickable {

    private Field fieldController;
    private final Parameter<Integer> maxBogsOnMap;
    private final Parameter<Integer> ticksToUpdateBogs;
    private final Parameter<Integer> maxBogLifetime;
    private final Parameter<Integer> minBogLifetime;
    private Random random = new Random();
    private int tick;
    private boolean startGame = true;
    private List<Bog> bogs;


    public BogController(Field fieldController, GameSettings gameSettings, List<Bog> bogs) {
        this.fieldController = fieldController;
        // settings from admin's page
        ticksToUpdateBogs = gameSettings.getTicksToUpdateBogs();
        maxBogsOnMap = gameSettings.getMaxBogsOnMap();
        maxBogLifetime = gameSettings.getMaxBogLifetime();
        minBogLifetime = gameSettings.getMinBogLifetime();
        this.bogs = bogs;
    }

    private void createNewBogs() {
        if (!hasCorrectSettings()) {
            return;
        }

        LengthToXY xy = new LengthToXY(fieldController.size());
        final int numberOfBogsForCreation =
                maxBogsOnMap.getValue() - fieldController.getBogs().size();
        int currentBogCount = 0;
        int lifeCount = 0;
        boolean fieldOccupied;
        int coverage = fieldController.size() * fieldController.size();

        if (tick >= ticksToUpdateBogs.getValue() || startGame) {
            while (currentBogCount < numberOfBogsForCreation) {

                int index = random.nextInt(coverage);
                lifeCount = minBogLifetime.getValue() + random.nextInt(maxBogLifetime.getValue()); // время жизни для вновь созданных ежей
                fieldOccupied = fieldController.isFieldOccupied(xy.getXY(index).getX(), xy.getXY(index).getY());
                if (!fieldOccupied) {
                    bogs.add(new Bog(xy.getXY(index), lifeCount));
                    currentBogCount++;
                }
            }
            tick = 0;
            startGame = false;
        } else {
            tick++;
        }
    }

    private boolean hasCorrectSettings() {
        return maxBogLifetime.getValue() > 0 && maxBogsOnMap.getValue() > 0;
    }

    private void removeDeadBogs() {
        bogs.removeIf(h -> !h.isAlive());
    }

    @Override
    public void tick() {
        bogs.forEach(Bog::tick);
        removeDeadBogs();
        createNewBogs();
    }
}
