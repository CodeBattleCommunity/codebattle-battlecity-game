package com.codenjoy.dojo.battlecity.model;

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


import com.codenjoy.dojo.services.EventListener;
import com.codenjoy.dojo.services.Game;
import com.codenjoy.dojo.services.Joystick;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.hero.HeroData;
import com.codenjoy.dojo.services.hero.HeroDataImpl;
import com.codenjoy.dojo.services.printer.Printer;
import com.codenjoy.dojo.services.printer.PrinterFactory;

import java.lang.reflect.Constructor;

public class Single implements Game {    // TODO test me

    private Player player;
    private Battlecity game;
    private Printer<String> printer;

    public Single(Battlecity game, EventListener listener, PrinterFactory factory, TankFactory playerTankFactory) {
        this.game = game;
        this.player = new Player(listener, playerTankFactory);
        this.printer = factory.getPrinter(game.reader(), player);
    }

    @Override
    public Joystick getJoystick() {
        return player.getTank();
    }

    @Override
    public int getMaxScore() {
        return 0;
    }

    @Override
    public int getCurrentScore() {
        return 0;
    }

    @Override
    public boolean isGameOver() {
        return !player.getTank().isAlive();
    }

    @Override
    public void newGame() {
        game.newGame(player);
    }

    @Override
    public String getBoardAsString() {
        return printer.print();
    }

    @Override
    public void destroy() {
        game.remove(player);
    }

    @Override
    public void clearScore() { }

    @Override
    public HeroData getHero() {
        try {
            return getHeroData(player, true,
                    new GamePlayerAdditionalInfo.Builder()
                            .setAmmo(player.getTank().getAmmunition().getAmmoCount())
                            .setLife(player.getTank().getLifeCount())
                            .createGamePlayerAdditionalInfo());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static HeroData getHeroData(Player player, boolean isSingleMode, Object additionalParameters) throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /**
         * Uses reflection to create HeroData to pass additional parameters
         * due to package-private access of HeroDataImpl constructor
         */
        Constructor<? extends HeroDataImpl> constructor = HeroDataImpl.class.getDeclaredConstructor(
                int.class, Point.class, boolean.class, Object.class);
        constructor.setAccessible(true);

        HeroDataImpl heroData = constructor.newInstance(0, player.getTank(), isSingleMode, additionalParameters);
        return heroData;
    }

    @Override
    public String getSave() {
        return null;
    }

    @Override
    public void tick() {
        game.tick();
    }

    public Player getPlayer() {
        return player;
    }
}
