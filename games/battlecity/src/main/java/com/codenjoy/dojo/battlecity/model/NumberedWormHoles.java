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

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class NumberedWormHoles extends PointImpl implements WormHole {

    private List<Point> linkedWormHoles;

    public NumberedWormHoles(int x, int y, List<Point> linkedWormHoles) {
        super(x, y);
        this.linkedWormHoles = linkedWormHoles;
    }

    @Override
    public Optional<Point> getExit(Direction direction, Field field) {
        List<Point> freeExits = linkedWormHoles.stream()
                .filter(h -> !field.isFieldOccupiedByTank(h.getX(), h.getY()))
                .collect(Collectors.toList());

        if (!freeExits.isEmpty()) {
            if (freeExits.size() == 1) {
                return Optional.of(freeExits.get(0));
            }

            int randomIndex = getRandom().nextInt(freeExits.size());
            return Optional.of(freeExits.get(randomIndex));
        }
        return Optional.empty();
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.WORM_HOLE;
    }

    private Random getRandom() {
        return ThreadLocalRandom.current();
    }
}
