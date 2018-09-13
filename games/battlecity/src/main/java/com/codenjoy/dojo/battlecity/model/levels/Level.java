package com.codenjoy.dojo.battlecity.model.levels;

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


import com.codenjoy.dojo.battlecity.model.*;
import com.codenjoy.dojo.battlecity.model.obstacle.*;
import com.codenjoy.dojo.services.*;
import com.codenjoy.dojo.services.printer.BoardReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Level implements Field {

    private final LengthToXY xy;
    private TankFactory aiTankFactory;

    private String map;
    private GameSettings gameSettings;

    public Level(String map, TankFactory aiTankFactory, GameSettings gameSettings) {
        this.map = map;
        this.gameSettings = gameSettings;

        removeSpaces();
        xy = new LengthToXY(size());
        this.aiTankFactory = aiTankFactory;
    }

    private void removeSpaces() {
        String result = "";
        for (int i = 0; i < map.length(); i += 2) {
            result += map.charAt(i);
        }
        map = result;
    }

    @Override
    public int size() {
        return (int) Math.sqrt(map.length());
    }


    @Override
    public List<Construction> getConstructions() {
        List<Construction> result = new LinkedList<Construction>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.CONSTRUCTION.ch) {
                result.add(new Construction(xy.getXY(index)));
            }
        }
        return result;
    }

    @Override
    public boolean isAmmoBonus(int x, int y) {
        return false; // do nothing
    }

    @Override
    public boolean isMedKitBonus(int x, int y) {
        return false;
    }

    @Override
    public AmmoBonus getAmmoBonus(int newX, int newY) {
        return null;
    }

    @Override
    public List<AmmoBonus> getAmmoBonuses() {
        List<AmmoBonus> result = new LinkedList<>();

        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.BONUS_AMMO.ch) {
                Point pt = xy.getXY(index);
                result.add(new AmmoBonus(pt));
            }
        }
        return result;
    }

    @Override
    public List<MedKitBonus> getMedKitBonuses() {
        List<MedKitBonus> result = new LinkedList<>();

        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.MEDKIT.ch) {
                Point pt = xy.getXY(index);
                result.add(new MedKitBonus(pt, gameSettings.getMaxMedKitBonusLifeTime().getValue()));
            }
        }
        return result;
    }

    @Override
    public boolean isBarrier(int x, int y) {
        return false;
    }



    @Override
    public boolean isWormHole(int x, int y) {
        return false;
    }

    @Override
    public boolean isObstacle(int x, int y) {
        return false;
    }

    @Override
    public boolean outOfField(int x, int y) {
        return false;  // do nothing
    }

    @Override
    public void affect(Bullet bullet) {
        // do nothing
    }

    @Override
    public List<Bullet> getBullets() {
        return new LinkedList<>(); // do nothing
    }

    @Override
    public BoardReader reader() {
        return new BoardReader() {
            @Override
            public int size() {
                return Level.this.size();
            }

            @Override
            public Iterable<? extends Point> elements() {
                List<Point> result = new LinkedList<>();
                result.addAll(Level.this.getBorders());
                result.addAll(Level.this.getAmmoBonuses());
                result.addAll(Level.this.getMedKitBonuses());
                result.addAll(Level.this.getWormHoles());
                result.addAll(Level.this.getBogs());
                result.addAll(Level.this.getSands());
                result.addAll(Level.this.getMoats());
                result.addAll(Level.this.getBullets());
                result.addAll(Level.this.getConstructions());
                result.addAll(Level.this.getTanks());
                result.addAll(Level.this.getHedgeHogs());
                return result;
            }
        };
    }

    @Override
    public List<Tank> getTanks() {
        List<Tank> result = new LinkedList<Tank>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.AI_TANK_DOWN.ch) {
                Point pt = xy.getXY(index);
                result.add(aiTankFactory.createTank(
                        TankParams.newAITankParams(pt.getX(), pt.getY(), Direction.DOWN)));
            }
        }
        return result;
    }

    @Override
    public List<Border> getBorders() {
        List<Border> result = new LinkedList<Border>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.BATTLE_WALL.ch) {
                result.add(new Border(xy.getXY(index)));
            }
        }
        return result;
    }

    @Override
    public List<HedgeHog> getHedgeHogs() {
        List<HedgeHog> result = new LinkedList<>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.HEDGEHOG.ch) {
                result.add(new HedgeHog(xy.getXY(index)));
            }
        }
        return result;
    }

    @Override
    public List<WormHole> getWormHoles() {
        return Stream.of(getSimpleWormHoles(),
                getNumberedWormHoles())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<WormHole> getNumberedWormHoles() {
        List<PointWithChar> numberedWormHolesPoints = new ArrayList<>();

        for (int index = 0; index < map.length(); index++) {
            if (Elements.TELEPORT_CHARS.contains(map.charAt(index))) {
                numberedWormHolesPoints.add(
                        new PointWithChar(map.charAt(index), xy.getXY(index)));
            }
        }

        Map<Character, List<Point>> map = new HashMap<>();

        for (PointWithChar p : numberedWormHolesPoints) {
            if (!map.containsKey(p.ch)) {
                map.put(p.ch, new ArrayList<>());
            }
            map.get(p.ch).add(p.point);
        }

        List<WormHole> numberedWormHoles = new ArrayList<>();

        for (PointWithChar p : numberedWormHolesPoints) {
            List<Point> allLinkedPoints = map.get(p.ch);

            numberedWormHoles.add(new NumberedWormHoles(p.point.getX(), p.point.getY(),
                    allLinkedPoints.stream()
                            .filter(point -> !point.equals(p.point))
            .collect(Collectors.toList())));
        }

        return numberedWormHoles;
    }

    private static class PointWithChar {
        char ch;
        Point point;

        public PointWithChar(char ch, Point point) {
            this.ch = ch;
            this.point = point;
        }
    }

    private List<WormHole> getSimpleWormHoles() {
        List<WormHole> simpleWormHoles = new LinkedList<>();

        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.WORM_HOLE.ch) {
                simpleWormHoles.add(new SimpleWormHoleImpl(xy.getXY(index), simpleWormHoles));
            }
        }
        return simpleWormHoles;
    }

    @Override
    public WormHole getWormHole(int newX, int newY) {
        return null;
    }

    @Override
    public boolean isFieldOccupied(int x, int y) {
        return false;
    }

    @Override
    public List<Bog> getBogs() {
        List<Bog> result = new LinkedList<>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.BOG.ch) {
                result.add(new Bog(xy.getXY(index)));
            }
        }
        return result;
    }

    @Override
    public List<Sand> getSands() {
        List<Sand> result = new LinkedList<>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.SAND.ch) {
                result.add(new Sand(xy.getXY(index)));
            }
        }
        return result;
    }

    @Override
    public List<Moat> getMoats() {
        List<Moat> result = new LinkedList<>();
        for (int index = 0; index < map.length(); index++) {
            if (map.charAt(index) == Elements.MOAT_HORIZONTAL.ch) {
                result.add(new Moat(xy.getXY(index), MoatType.HORIZONTAL));
            }
            if (map.charAt(index) == Elements.MOAT_VERTICAL.ch) {
                result.add(new Moat(xy.getXY(index), MoatType.VERTICAL));
            }
        }
        return result;
    }

    @Override
    public Obstacle getObstacle(int x, int y) {
        return null; // do nothing
    }

    @Override
    public MedKitBonus getMedKitBonus(int newX, int newY) {
        return null;
    }
}
