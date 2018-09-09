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

    public HealthBonus(Point point) {
        super(point);
    }

    public boolean isAlive() {
        return lifeCycle > 0;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        if (isAlive()) {
            return Elements.HEALTH_BONUS;
        } else {
            return Elements.NONE;
        }
    }

    @Override
    public void tick() {
        lifeCycle--;
    }
}
