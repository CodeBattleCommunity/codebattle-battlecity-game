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

public class SimpleWormHoleImpl extends PointImpl implements WormHole {

    private List<WormHole> allWormHoles;

    public SimpleWormHoleImpl(int x, int y, List<WormHole> allWormHoles) {
        super(x, y);
        this.allWormHoles = allWormHoles;
    }

    public SimpleWormHoleImpl(Point point, List<WormHole> allWormHoles) {
        super(point);
        this.allWormHoles = allWormHoles;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.WORM_HOLE;
    }

    @Override
    public Optional<Point> getExit(Direction direction) {
        if (Direction.DOWN.equals(direction) ||
                Direction.UP.equals(direction)) {

            return allWormHoles.stream()
                    .filter(hole -> x == hole.getX() && y != hole.getY())
                    .findAny()
                    .map(exitHole -> calculateTankExit(exitHole.getX(), exitHole.getY(), direction));

        } else {
            return allWormHoles.stream()
                    .filter(hole -> y == hole.getY() && x != hole.getX())
                    .findAny()
                    .map(exitHole -> calculateTankExit(exitHole.getX(), exitHole.getY(), direction));
        }
    }

    Point calculateTankExit(int exitX, int exitY, Direction direction) {
        switch (direction) {
            case UP: return XY(exitX, exitY + 1);
            case DOWN: return XY(exitX, exitY - 1);
            case LEFT: return XY(exitX - 1, exitY);
            default:
                return XY(exitX + 1, exitY);
        }
    }

    private Point XY(int x, int y) {
        return new PointImpl(x, y);
    }
}
