package com.codenjoy.dojo.battlecity.model.controller.elements;

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
import com.codenjoy.dojo.battlecity.model.controller.ElementController;
import com.codenjoy.dojo.battlecity.model.controller.ElementControllerSettings;
import com.codenjoy.dojo.battlecity.model.obstacle.Bog;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;

import java.util.List;

public class BogController extends ElementController<Bog>  {

    public BogController(Field fieldController, GameSettings settings, List<Bog> elements, Dice dice) {
        super(fieldController, settings, elements, dice);
    }

    @Override
    protected Bog createNewElement(Point point, int lifeCount) {
        return new Bog(point, lifeCount);
    }

    @Override
    protected ElementControllerSettings getElementSettings(GameSettings gameSettings) {
        ElementControllerSettings settings = new ElementControllerSettings();
        settings.setMinElementsOnMap(gameSettings.getMinBogsOnMap());
        settings.setMaxElementsOnMap(gameSettings.getMaxBogsOnMap());
        settings.setMaxElementLifetime(gameSettings.getMaxBogLifetime());
        settings.setMinElementLifetime(gameSettings.getMinBogLifetime());
        settings.setTicksToUpdate(gameSettings.getTicksToUpdateBogs());

        return settings;
    }
}
