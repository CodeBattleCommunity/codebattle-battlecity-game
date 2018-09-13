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
        assertThat(settings.getAmmoQuantityInAmmoBonus(), equalTo(Optional.of(5)));
        assertThat(settings.getMaxHedgeHogsOnMap(), equalTo(Optional.of(6)));
        assertThat(settings.getTicksToUpdateHedgehogs(), equalTo(Optional.of(7)));
        assertThat(settings.getMaxHedgehogLifetime(), equalTo(Optional.of(8)));
        assertThat(settings.getMinHedgehogLifetime(), equalTo(Optional.of(9)));
        assertThat(settings.getPlayerTicksPerBullet(), equalTo(Optional.of(10)));
        assertThat(settings.getAiTicksPerBullet(), equalTo(Optional.of(11)));
        assertThat(settings.getInitialPlayerLivesCount(), equalTo(Optional.of(12)));
        assertThat(settings.getInitialAILivesCount(), equalTo(Optional.of(13)));
        assertThat(settings.getMedKitBonusLifeCycle(), equalTo(Optional.of(14)));
        assertThat(settings.getMinMedKitBonusOnMap(), equalTo(Optional.of(15)));
        assertThat(settings.getMaxMedKitBonusOnMap(), equalTo(Optional.of(16)));
        assertThat(settings.getMinMedKitBonusLifeTime(), equalTo(Optional.of(17)));
        assertThat(settings.getMaxMedKitBonusLifeTime(), equalTo(Optional.of(18)));
        assertThat(settings.getMinAmmoBonusLifeCycle(), equalTo(Optional.of(19)));
        assertThat(settings.getMaxAmmoBonusLifeCycle(), equalTo(Optional.of(20)));
        assertThat(settings.getAmmoBonusGenerationCycle(), equalTo(Optional.of(21)));
    }

    private ByteArrayInputStream getInputStream(String content) {
        return new ByteArrayInputStream(content.getBytes());
    }

    private static final String YML_CONTENT = "---\n" +
            "gameMode: gameMode\n" +
            "initialPlayerAmmoCount: 1\n" +
            "initialAIAmmoCount: 2\n" +
            "ammoBonusCountOnMap: 3\n" +
            "ammoQuantityInAmmoBonus: 5\n" +
            "maxHedgeHogsOnMap: 6\n" +
            "ticksToUpdateHedgehogs: 7\n" +
            "maxHedgehogLifetime: 8\n" +
            "minHedgehogLifetime: 9\n" +
            "playerTicksPerBullet: 10\n" +
            "aiTicksPerBullet: 11\n" +
            "initialPlayerLivesCount: 12\n" +
            "initialAILivesCount: 13\n" +
            "medKitBonusLifeCycle: 14\n" +
            "minMedKitBonusOnMap: 15\n" +
            "maxMedKitBonusOnMap: 16\n" +
            "minMedKitBonusLifeTime: 17\n" +
            "maxMedKitBonusLifeTime: 18\n" +
            "minAmmoBonusLifeCycle: 19\n" +
            "maxAmmoBonusLifeCycle: 20\n" +
            "ammoBonusGenerationCycle: 21\n" +
            "";
}
