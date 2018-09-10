package com.codenjoy.dojo.battlecity.model;

import com.codenjoy.dojo.services.settings.Parameter;

public class Health {

    private int healthCount;
    private Parameter<Integer> initialHealthCount;

    public Health(Parameter<Integer> initialHealthCount) {
        this.healthCount = initialHealthCount.getValue();
        this.initialHealthCount = initialHealthCount;
    }

    public int getHealthCount() {
        return healthCount;
    }

    public void getHealthBonus(int healthBonus) {
        healthCount += healthBonus;
    }

    public void refreshHealth() {
        healthCount = initialHealthCount.getValue();
    }

    public void getDamage() {
        healthCount--;
    }

    public boolean isAlive() {
        return healthCount > 0;
    }

}
