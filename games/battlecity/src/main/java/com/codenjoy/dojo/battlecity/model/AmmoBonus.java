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


import com.codenjoy.dojo.services.*;


public class AmmoBonus extends PointImpl implements ManagedElement, State<Elements, Player> {



    private int lifeCycle;
    private static final int DEFAULT_LIFE_CYCLE = 10;
    private int ammoQuantity;
    private static final int DEFAULT_AMMO_QUANTITY = 5;


    private boolean alive;
    protected Field field;
    private int timer ;


    public AmmoBonus(int x, int y) {
        super(x,y);
        alive = true;
        this.lifeCycle = DEFAULT_LIFE_CYCLE;
        this.ammoQuantity = DEFAULT_AMMO_QUANTITY;
        this.timer = 0;
    }

    public AmmoBonus(Point xy, int ammoBonusLifeCycle,int ammoQuantity) {
        super(xy);
        alive = true;
        this.lifeCycle = ammoBonusLifeCycle;
        this.ammoQuantity = ammoQuantity;
        this.timer = 0;

    }
    public AmmoBonus(Point xy) {
        super(xy);
        alive = true;
        this.lifeCycle = DEFAULT_LIFE_CYCLE;
        this.ammoQuantity = DEFAULT_AMMO_QUANTITY;
        this.timer = 0;

    }


    @Override
    public Elements state(Player player, Object... objects) {
        if (isAlive()) {
            return Elements.BONUS_AMMO;
        } else {
            return Elements.NONE;
        }
    }

    @Override
    public void tick() {
        if (timer >= lifeCycle) {
            timer = 0;
            alive = false;
        } else {
            timer++;
        }
    }


    public void pickedUp() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }


    public int getAmmoQuantity() {
        return ammoQuantity;
    }


}
