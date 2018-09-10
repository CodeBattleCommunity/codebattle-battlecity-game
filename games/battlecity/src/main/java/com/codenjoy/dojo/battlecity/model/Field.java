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


import com.codenjoy.dojo.battlecity.model.obstacle.Bog;
import com.codenjoy.dojo.battlecity.model.obstacle.Moat;
import com.codenjoy.dojo.battlecity.model.obstacle.Obstacle;
import com.codenjoy.dojo.battlecity.model.obstacle.Sand;
import com.codenjoy.dojo.services.printer.BoardReader;

import java.util.List;

public interface Field {
    int size();

    List<Border> getBorders();

    List<HedgeHog> getHedgeHogs();

    List<Tank> getTanks();

    List<Construction> getConstructions();

    boolean isAmmoBonus(int x, int y);

    boolean isHealthBonus(int x, int y);

    AmmoBonus getAmmoBonus(int newX, int newY);

    List<AmmoBonus> getAmmoBonuses();

    boolean isBarrier(int x, int y);

    boolean isFieldOccupied(int x, int y);

    boolean isWormHole(int x, int y);

    boolean isObstacle(int x, int y);

    List<Bog> getBogs();

    List<Sand> getSands();

    List<Moat> getMoats();

    boolean outOfField(int x, int y);

    void affect(Bullet bullet);

    HealthBonus getHealthBonus(int x, int y);

    List<HealthBonus> getHealthBonuses();

    List<Bullet> getBullets();

    List<WormHole> getWormHoles();

    BoardReader reader();

    WormHole getWormHole(int newX, int newY);


    Obstacle getObstacle(int x, int y);
}
