package com.codenjoy.dojo.battlecity.model;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
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


import com.codenjoy.dojo.battlecity.model.events.Event;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.EventListener;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

public class Player {
    private Tank tank;
    private TankFactory playerTankFactory;
    private Point startPosition;
    private Direction startDirection;
    private EventListener listener;

    public Player(EventListener listener, TankFactory playerTankFactory, Point startPosition, Direction startDirection) {
        this.listener = listener;
        this.playerTankFactory = playerTankFactory;
        this.startPosition = startPosition;
        this.startDirection = startDirection;

        tank = createTank(startPosition, startDirection);
    }

    public Player(EventListener listener, TankFactory playerTankFactory) {
        this(listener, playerTankFactory, new PointImpl(0, 0), Direction.UP);
    }

    private Tank createTank(Point startPosition, Direction startDirection) {
        Tank tank = playerTankFactory.createTank(
                TankParams.newTankParams(startPosition.getX(), startPosition.getY(), startDirection));

        return tank;
    }

    public Tank getTank() {
        return tank;
    }

    public void event(Event event) {
        if (listener != null) {
            listener.event(event);
        }
    }

    private void gameOver() {
        tank.kill(null);
    }

    public void newHero(Battlecity game) {
        createNewPlayerTank(game);
        tank.locateTankOnPositionOrRandonly(game);
    }

    private void createNewPlayerTank(Battlecity tanks) {
        tanks.getTanks().remove(tank);
        tank = createTank(startPosition, startDirection);
        tanks.getTanks().add(tank);
    }
}
