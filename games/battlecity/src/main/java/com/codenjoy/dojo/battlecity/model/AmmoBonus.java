package com.codenjoy.dojo.battlecity.model;

import com.codenjoy.dojo.services.*;


public class AmmoBonus extends PointImpl implements Tickable, State<Elements ,Player> {

    private Elements ch;
    private boolean alive;
    private Field field;
    private Dice dice;

    public AmmoBonus(int x, int y) {
        super(x, y);
        ch = Elements.BONUS_AMMO;
    }

    public AmmoBonus(Point xy) {
        this(xy.getX(), xy.getY());
    }


    @Override
    public Elements state(Player player, Object... objects) {
        return null;
    }

    @Override
    public void tick() {

    }

    public void pickedUp() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setField(Field field) {
        this.field = field;
        int xx = x;
        int yy = y;
        while (field.isBarrier(xx, yy)) {
            xx = dice.next(field.size());
            yy = dice.next(field.size());
        }
        x = xx;
        y = yy;
        alive = true;
    }
}
