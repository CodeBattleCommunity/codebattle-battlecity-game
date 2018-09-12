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

public class SimpleHealth implements Health {
    private int lives;

    public SimpleHealth(int startLives) {
        this.lives = startLives;
    }

    @Override
    public boolean isAlive() {
        return lives > 0;
    }

    @Override
    public void doDamage(int damagePower) {
        lives -= damagePower;

        if (lives < 0) {
            lives = 0;
        }
    }

    @Override
    public void addLives(int livesCount) {
        lives += livesCount;
    }
}
