package com.codenjoy.dojo.battlecity.model.levels;

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

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class LevelSettingsLoaderImplTest {


    private YmlLevelSettingsLoaderImpl underTests = new YmlLevelSettingsLoaderImpl();

    @Test
    public void loadLevelSettings() {
        InputStream is = getInputStream(YML_CONTENT);

        LevelSettings settings = underTests.loadLevelSettings(is);

        assertThat(settings.getGameMode(), equalTo(Optional.of("gameMode")));
        assertThat(settings.getInitialPlayerAmmoCount(), equalTo(Optional.of(1)));
        assertThat(settings.getInitialAIAmmoCount(), equalTo(Optional.of(2)));
        assertThat(settings.getAmmoBonusCountOnMap(), equalTo(Optional.of(3)));
        assertThat(settings.getAmmoBonusLifeCycle(), equalTo(Optional.of(4)));
        assertThat(settings.getAmmoQuantityInAmmoBonus(), equalTo(Optional.of(5)));
        assertThat(settings.getMaxHedgeHogsOnMap(), equalTo(Optional.of(6)));
        assertThat(settings.getTicksToUpdateHedgehogs(), equalTo(Optional.of(7)));
        assertThat(settings.getMaxHedgehogLifetime(), equalTo(Optional.of(8)));
        assertThat(settings.getMinHedgehogLifetime(), equalTo(Optional.of(9)));
    }

    private ByteArrayInputStream getInputStream(String content) {
        return new ByteArrayInputStream(content.getBytes());
    }

    private static final String YML_CONTENT = "---\n" +
            "gameMode: gameMode\n" +
            "initialPlayerAmmoCount: 1\n" +
            "initialAIAmmoCount: 2\n" +
            "ammoBonusCountOnMap: 3\n" +
            "ammoBonusLifeCycle: 4\n" +
            "ammoQuantityInAmmoBonus: 5\n" +
            "maxHedgeHogsOnMap: 6\n" +
            "ticksToUpdateHedgehogs: 7\n" +
            "maxHedgehogLifetime: 8\n" +
            "minHedgehogLifetime: 9\n" +
            "";
}
