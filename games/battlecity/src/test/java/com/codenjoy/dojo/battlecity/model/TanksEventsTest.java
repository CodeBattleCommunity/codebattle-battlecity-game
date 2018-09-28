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


import com.codenjoy.dojo.battlecity.matchers.EqualFields;
import com.codenjoy.dojo.battlecity.model.events.Event;
import com.codenjoy.dojo.battlecity.model.events.YouKilledTankEvent;
import com.codenjoy.dojo.battlecity.model.events.YourTankWasKilledEvent;
import com.codenjoy.dojo.battlecity.model.levels.LevelInfo;
import com.codenjoy.dojo.battlecity.model.levels.LevelRegistry;
import com.codenjoy.dojo.battlecity.model.levels.LevelSettingsImpl;
import com.codenjoy.dojo.battlecity.model.modes.BattlecityClassicGameMode;
import com.codenjoy.dojo.battlecity.model.modes.GameModeRegistry;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.EventListener;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.services.printer.PrinterFactory;
import com.codenjoy.dojo.services.printer.PrinterFactoryImpl;

import org.hamcrest.Description;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class TanksEventsTest {

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
        player = player(1, 1, Direction.UP, events);
    }

    @Test
    public void shouldKillAiTankEvent() {
        enemyPlayer = player(1, 5, Direction.DOWN);

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼↥    ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        noEvents(events);

        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼Ѡ    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        onlyEvent(events, new YouKilledTankEvent(Tank.Type.Player));
    }

    @Test
    public void shouldKillMyTankByAIEvent() {
        enemyPlayer = player(1, 5, Direction.DOWN);

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        enemyPlayer.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼↧    ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        noEvents(events);

        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼Ѡ    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        onlyEvent(events, new YourTankWasKilledEvent());
    }

    @Test
    public void shouldKillOtherPlayerTankEvent() {
        enemyPlayer = player(1, 5, Direction.DOWN);

        EventListener events2 = mock(EventListener.class);
        Player player2 = player(5, 1, Direction.UP, events2);

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲   ˄☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().right();
        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼ ►↦ ˄☼\n" +
                "☼☼☼☼☼☼☼\n");

        noEvents(events);
        noEvents(events2);

        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼ ►  Ѡ☼\n" +
                "☼☼☼☼☼☼☼\n");

        onlyEvent(events, new YouKilledTankEvent(Tank.Type.Player));
        onlyEvent(events2, new YourTankWasKilledEvent());
    }

    @Test
    public void shouldKillMyTankByOtherPlayerTankEvent() {
        enemyPlayer = player(1, 5, Direction.DOWN);

        EventListener events2 = mock(EventListener.class);
        Player player2 = player(5, 1, Direction.UP, events2);
        Tank tank2 = player2.getTank();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲   ˄☼\n" +
                "☼☼☼☼☼☼☼\n");

        tank2.left();
        tank2.act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲ ↤˂ ☼\n" +
                "☼☼☼☼☼☼☼\n");

        noEvents(events);
        noEvents(events2);

        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼Ѡ  ˂ ☼\n" +
                "☼☼☼☼☼☼☼\n");

        onlyEvent(events, new YourTankWasKilledEvent());
        onlyEvent(events2, new YouKilledTankEvent(Tank.Type.Player));
    }

    private void noEvents(EventListener ev) {
        Mockito.verifyNoMoreInteractions(ev);
        reset(events);
    }

    @Test
    public void shouldIKillOtherTankWhenKillMeByAi() {
        enemyPlayer = player(1, 5, Direction.DOWN);

        EventListener events2 = mock(EventListener.class);
        Player player2 = player(5, 1, Direction.UP, events2);

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲   ˄☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().turn(Direction.RIGHT);
        enemyPlayer.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼↧    ☼\n" +
                "☼     ☼\n" +
                "☼►   ˄☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼Ѡ ↦ ˄☼\n" +
                "☼☼☼☼☼☼☼\n");

        onlyEvent(events, new YourTankWasKilledEvent());
        noEvents(events2);

        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼    ˄☼\n" +
                "☼☼☼☼☼☼☼\n");

        noEvents(events);
        noEvents(events2);
    }

    private void onlyEvent(EventListener ev, Event event, Event... otherEvents) {
        ArgumentCaptor<Event> fooCaptor = ArgumentCaptor.forClass(Event.class);

        Mockito.verify(ev, times(1 + otherEvents.length)).event(fooCaptor.capture());

        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(event);
        expectedEvents.addAll(Arrays.stream(otherEvents).collect(Collectors.toList()));

        List<Event> allValues = fooCaptor.getAllValues();

        for (int i = 0; i < allValues.size(); i++) {
            assertThat(allValues.get(i).getClass(), equalTo(expectedEvents.get(i).getClass()));
        }

        noEvents(ev);
        reset(events);
    }

    private void assertD(String field) {
        assertThat(printerFactory.getPrinter(game.reader(), player).print(),
                new EqualFields<>(field));
    }

    @Test
    public void shouldMyBulletsRemovesWhenKillMe() {
        enemyPlayer = player(1, 5, Direction.DOWN);

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().turn(Direction.RIGHT);
        enemyPlayer.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼↧    ☼\n" +
                "☼     ☼\n" +
                "☼►    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼˅    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼Ѡ ↦  ☼\n" +
                "☼☼☼☼☼☼☼\n");


        assertFalse(player.getTank().isAlive());
        game.newGame(player);
        game.tick();
    }

    @Test
    public void bugWhenBulletDoesNotHitATankUp() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(1, 3, Direction.UP, events2);

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼˄    ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().up();
        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼˄    ☼\n" +
                "☼↥    ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().up();
        game.tick();
        assertD("☼☼☼☼☼☼☼\n" +
                "☼Ѡ    ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void bugWhenBulletDoesNotHitATankRight() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(3, 1, Direction.RIGHT, events2);

        player.getTank().direction = Direction.RIGHT;

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ˃  ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().right();
        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ↦˃ ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().right();
        game.tick();
        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼►   Ѡ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void bugWhenBulletDoesNotHitATankRight_2() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(2, 1, Direction.RIGHT, events2);

        player.getTank().direction = Direction.RIGHT;

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼►˃   ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().right();
        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► Ѡ  ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void bugWhenBulletDoesNotHitATankRight_3() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(3, 1, Direction.RIGHT, events2);

        player.getTank().direction = Direction.RIGHT;

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ˃  ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().right();
        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ↦˃ ☼\n" +
                "☼☼☼☼☼☼☼\n");

        game.tick();
        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼►  Ѡ ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void bugWhenBulletDoesNotHitATankRight_4() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(3, 1, Direction.RIGHT, events2);

        player.getTank().direction = Direction.RIGHT;

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ˃  ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().right();
        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ↦˃ ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().left();

        game.tick();
        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► Ѡ  ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void bugWhenBulletDoesNotHitATankRight_5() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(3, 1, Direction.RIGHT, events2);

        player.getTank().direction = Direction.RIGHT;

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ˃  ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().right();
        player.getTank().act();
        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼► ↦˃ ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().act();
        player2.getTank().right();

        game.tick();
        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼►   Ѡ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void tanksShouldBeKilledIfTheyAreAtAdjacentCellsAndFiring() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(2, 1, Direction.LEFT, events2);

        player.getTank().direction = Direction.RIGHT;

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼►˂   ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().act();
        player.getTank().act();

        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼ѠѠ   ☼\n" +
                "☼☼☼☼☼☼☼\n");

        onlyEvent(events, new YourTankWasKilledEvent(), new YouKilledTankEvent(Tank.Type.Player));
    }

    @Test
    public void tankShouldSurviveIfIsEscapedFromCellUnderFire() {
        EventListener events2 = mock(EventListener.class);
        Player player2 = player(2, 1, Direction.LEFT, events2);

        player.getTank().direction = Direction.RIGHT;

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼►˂   ☼\n" +
                "☼☼☼☼☼☼☼\n");

        player2.getTank().act();
        player.getTank().act();
        player.getTank().up();

        game.tick();

        assertD("☼☼☼☼☼☼☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼     ☼\n" +
                "☼▲    ☼\n" +
                "☼ Ѡ   ☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    private Player player(int x, int y, Direction direction, EventListener listener) {
        Player player = utils.player(x, y, direction, listener, playerTankFactory);
        game.newGame(player);
        return player;
    }

    private Player player(int x, int y, Direction direction) {
        return player(x, y, direction, mock(EventListener.class));
    }
}
