package com.codenjoy.dojo.battlecity.model.controller;

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


import com.codenjoy.dojo.services.settings.Parameter;

public class ElementControllerSettings {
    private Parameter<Integer> ticksToUpdate;
    private Parameter<Integer> maxElementsOnMap;
    private Parameter<Integer> maxElementLifetime;
    private Parameter<Integer> minElementLifetime;

    public Parameter<Integer> getTicksToUpdate() {
        return ticksToUpdate;
    }

    public void setTicksToUpdate(Parameter<Integer> ticksToUpdate) {
        this.ticksToUpdate = ticksToUpdate;
    }

    public Parameter<Integer> getMaxElementsOnMap() {
        return maxElementsOnMap;
    }

    public void setMaxElementsOnMap(Parameter<Integer> maxElementsOnMap) {
        this.maxElementsOnMap = maxElementsOnMap;
    }

    public Parameter<Integer> getMaxElementLifetime() {
        return maxElementLifetime;
    }

    public void setMaxElementLifetime(Parameter<Integer> maxElementLifetime) {
        this.maxElementLifetime = maxElementLifetime;
    }

    public Parameter<Integer> getMinElementLifetime() {
        return minElementLifetime;
    }

    public void setMinElementLifetime(Parameter<Integer> minElementLifetime) {
        this.minElementLifetime = minElementLifetime;
    }
}
