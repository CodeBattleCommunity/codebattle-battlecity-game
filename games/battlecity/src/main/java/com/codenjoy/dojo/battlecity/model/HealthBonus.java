package com.codenjoy.dojo.battlecity.model;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;
import com.codenjoy.dojo.services.Tickable;

public class HealthBonus extends PointImpl implements Tickable, State<Elements, Player> {

    private int lifeCycle;

    public HealthBonus(int x, int y) {
        super(x, y);
    }

    public HealthBonus(Point point, int lifeCycle) {
        super(point);
        this.lifeCycle = lifeCycle;
    }

    public HealthBonus(Point point) {
        super(point);
    }

    public boolean isAlive() {
        return lifeCycle > 0;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        if (isAlive()) {
            return Elements.MEDICINE;
        } else {
            return Elements.NONE;
        }
    }

    public void setLifeCycle(int lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    @Override
    public void tick() {
        lifeCycle--;
    }
}
