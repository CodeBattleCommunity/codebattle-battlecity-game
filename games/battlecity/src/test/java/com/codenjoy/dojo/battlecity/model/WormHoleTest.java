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

import com.codenjoy.dojo.battlecity.matchers.EqualFields;
import com.codenjoy.dojo.battlecity.model.levels.LevelInfo;
import com.codenjoy.dojo.battlecity.model.levels.LevelRegistry;
import com.codenjoy.dojo.battlecity.model.levels.LevelSettingsImpl;
import com.codenjoy.dojo.battlecity.model.modes.BattlecityClassicGameMode;
import com.codenjoy.dojo.battlecity.model.modes.GameModeRegistry;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.EventListener;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.services.printer.PrinterFactory;
import com.codenjoy.dojo.services.printer.PrinterFactoryImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WormHoleTest {

    private Battlecity game;
    private EventListener events;
    private Player player;
    private Player enemyPlayer;
    private BattlecityTest utils = new BattlecityTest();
    private PrinterFactory printerFactory = new PrinterFactoryImpl();
    private GameSettings gameSettings = new DefaultGameSettingsImpl();
    @Mock
    private LevelRegistry levelRegistry;
    @Mock
    private GameModeRegistry modeRegistry;
    TankFactory playerTankFactory;
    TankFactory aiTankFactory;

    @Before
    public void setup() {
        when(levelRegistry.getLevelByName("default")).thenReturn(new LevelInfo("☼ ☼ ☼ ☼ ☼ ☼ ☼\n" +
                "☼           ☼\n" +
                "☼           ☼\n" +
                "☼           ☼\n" +
                "☼           ☼\n" +
                "☼           ☼\n" +
                "☼ ☼ ☼ ☼ ☼ ☼ ☼\n", new LevelSettingsImpl()));

        playerTankFactory = new PlayerTankFactory(new RandomDice(), gameSettings);
        aiTankFactory = mock(TankFactory.class);

        game = new Battlecity(aiTankFactory, gameSettings, levelRegistry, null);
        game.setModeRegistry(modeRegistry);

        when(modeRegistry.getGameModeByName(any())).thenReturn(new BattlecityClassicGameMode(game.getGameController()));
        game.startOrRestartGame();

        events = mock(EventListener.class);
    }

    private Player player(int x, int y, Direction direction) {
        Player player = utils.player(x, y, direction, mock(EventListener.class), playerTankFactory);
        game.newGame(player);
        return player;
    }

    @Test
    public void tankShouldBeTeleportedToAnotherExit() {
        player = player(1, 2, Direction.DOWN);
        Player player2 = player(5, 4, Direction.LEFT);

        addNumberedTeleports(new PointImpl(1, 1), new PointImpl(5, 5));

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ʘ☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().down();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ▼☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }


    @Test
    public void tankShouldBeAbleToMoveAfterExitWasOccupiedByAnotherTank() {
        player = player(1, 2, Direction.DOWN);
        Player player2 = player(5, 4, Direction.LEFT);

        addNumberedTeleports(new PointImpl(1, 1), new PointImpl(5, 5));

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ʘ☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().move(5, 5);
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().down();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void tankShouldBeTeleportedUsingSimpleWormHoles() {
        player = player(1, 2, Direction.DOWN);
        Player player2 = player(2, 5, Direction.LEFT);

        addSimpleTeleports(new PointImpl(1, 1), new PointImpl(1, 5));

        assertD("☼☼☼☼☼☼☼\n" +
                "☼ʘ˂   ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().move(1, 5);
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˂    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().down();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˂    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void tankShouldBeMovedToTeleportPlaceIfExitIsOccupied() {
        player = player(1, 2, Direction.DOWN);

        addSimpleTeleports(new PointImpl(1, 1), new PointImpl(1, 5));

        assertD("☼☼☼☼☼☼☼\n" +
                "☼ʘ    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().down();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼ʘ    ☼\n" +
                "☼▼    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    private void addSimpleTeleports(Point point1, Point point2) {
        game.getWormHoles().addAll(
                Stream.of(point1, point2)
                        .map(p -> new SimpleWormHoleImpl(p, game.getWormHoles()))
                        .collect(Collectors.toList()));
    }

    @Test
    public void tankShouldBeAbleToMoveAfterExitWasOccupiedByAnotherTank3() {
        player = player(1, 2, Direction.DOWN);
        Player player2 = player(5, 4, Direction.LEFT);

        addNumberedTeleports(new PointImpl(1, 1), new PointImpl(5, 5));

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ʘ☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().move(5, 5);
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼ʘ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().down();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼    ˂☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▼    ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    private void assertD(String field) {
        assertThat(printerFactory.getPrinter(game.reader(), player).print(),
                new EqualFields<>(field));
    }

    private void addNumberedTeleports(Point point, Point... points) {
        ArrayList<Point> list = new ArrayList<>();
        list.add(point);
        list.addAll(Arrays.stream(points).collect(Collectors.toList()));


        List<NumberedWormHoles> numberedWormHoles = new ArrayList<>();

        list.forEach(p -> {
            numberedWormHoles.add(
                    new NumberedWormHoles(p.getX(), p.getY(),
                            list.stream()
                                    .filter(_point -> !_point.equals(p))
                                    .collect(Collectors.toList())));

        });

        game.getWormHoles().addAll(numberedWormHoles);
    }
}
