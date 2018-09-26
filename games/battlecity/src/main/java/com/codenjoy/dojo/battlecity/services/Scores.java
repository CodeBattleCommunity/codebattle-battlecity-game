package com.codenjoy.dojo.battlecity.services;

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


import com.codenjoy.dojo.battlecity.model.GameSettings;
import com.codenjoy.dojo.services.PlayerScores;
import com.codenjoy.dojo.services.ScoreData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Scores implements PlayerScores {

    private AtomicInteger killsCount;
    private AtomicInteger deathsCount;
    private GameSettings gameSettings;
    private AtomicReference<BigDecimal> efficiency;

    public Scores(ScoreData initScore, GameSettings gameSettings) {
        killsCount = new AtomicInteger(initScore.getKills());
        deathsCount = new AtomicInteger(initScore.getDeaths());
        this.gameSettings = gameSettings;
        efficiency = new AtomicReference<>(calculateEfficiency(killsCount.get(), deathsCount.get()));
    }

    private BigDecimal calculateEfficiency(int killsCount, int deathsCount) {
        return new BigDecimal(killsCount / (deathsCount + 1.0));
    }

    @Override
    public int clear() {
        killsCount.set(0);
        deathsCount.set(0);

        return (int)getScore();
    }

    @Override
    public Object getScore() {
        return efficiency.get()
                .setScale(1, RoundingMode.HALF_UP).intValue();
    }

    public int addKills(int kills) {
        if (isScoresRecordingEnabled()) {
            int newKills = killsCount.addAndGet(kills);
            updateEfficiency();

            return newKills;
        } else {
            return killsCount.get();
        }
    }

    public int addDeath() {
        if (isScoresRecordingEnabled()) {
            int newDeaths = deathsCount.incrementAndGet();
            updateEfficiency();

            return newDeaths;
        } else {
            return deathsCount.get();
        }
    }

    private Boolean isScoresRecordingEnabled() {
        return gameSettings.isScoreRecordingEnabled().getValue();
    }

    private void updateEfficiency() {
        BigDecimal efficiency = calculateEfficiency(killsCount.get(), deathsCount.get());
        this.efficiency.set(efficiency);
    }

    @Override
    public int getDeaths() {
        return deathsCount.get();
    }

    @Override
    public int getKills() {
        return killsCount.get();
    }

    @Override
    public BigDecimal getEfficiency() {
        return efficiency.get();
    }
}
