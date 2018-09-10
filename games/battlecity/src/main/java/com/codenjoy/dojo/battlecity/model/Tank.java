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


import com.codenjoy.dojo.battlecity.model.obstacle.ObstacleEffect;
import com.codenjoy.dojo.services.*;
import com.codenjoy.dojo.services.settings.Parameter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Tank extends MovingObject implements Joystick, Tickable, State<Elements, Player> {

    private Dice dice;
    private List<Bullet> bullets;
    private Ammunition ammunition;
    private Health health;
    protected Field field;
    private boolean alive;
    private Gun gun;
    private Direction previousDirection;
    private ObstacleEffect obstacleEffect;

    public Tank(int x, int y, Direction direction, Dice dice, int ticksPerBullets, Parameter<Integer> initialAmmo, Parameter<Integer> initialHealth) {
        super(x, y, direction);
        gun = new Gun(ticksPerBullets);
        bullets = new LinkedList<>();
        speed = 1;
        moving = false;
        alive = true;
        this.dice = dice;
        this.health = new Health(initialHealth);
        this.ammunition = new Ammunition(initialAmmo);
    }

    void turn(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void up() {
        direction = Direction.UP;
        moving = isTankMove();
    }

    @Override
    public void down() {
        direction = Direction.DOWN;
        moving = isTankMove();
    }

    @Override
    public void right() {
        direction = Direction.RIGHT;
        moving = isTankMove();
    }

    @Override
    public void left() {
        direction = Direction.LEFT;
        moving = isTankMove();
    }

    @Override
    public void moving(int newX, int newY) {
        if (field.isBarrier(newX, newY)) {
            // do nothing
        } else if (field.isWormHole(newX, newY)) {
            WormHole wormHole = field.getWormHole(newX, newY);
            Optional<Point> wormHoleExit = wormHole.getExit(getMovingDirection(x, y, newX, newY));

            wormHoleExit.ifPresent(p -> {
                if (!field.outOfField(p.getX(), p.getY()) && !field.isBarrier(p.getX(), p.getY())) {
                    setTankPosition(p.getX(), p.getY());
                }
            });
        }else  if (field.isAmmoBonus(newX, newY)) {
            AmmoBonus ammoBonus = field.getAmmoBonus(newX, newY);
            setTankPosition(newX, newY);
            ammoBonus.pickedUp();
            ammunition.replenishAmmo(ammoBonus.getAmmoQuantity());

        } else if (tankHasObstacleEffect()) {
            if (!obstacleEffect.isActive()) {
                removeObstacleEffect();
            }
        } else if (field.isObstacle(newX, newY)) {
            obstacleEffect = field.getObstacle(newX, newY).getObstacleEffect();
            setTankPosition(newX, newY);

        } else if (field.isHealthBonus(newX, newY)) {
            HealthBonus healthBonus = field.getHealthBonus(newX, newY);
            setTankPosition(newX, newY);
            healthBonus.setLifeCycle(0);
            health.getHealthBonus();
        } else {
            setTankPosition(newX, newY);
        }

        moving = false;
    }

    private void removeObstacleEffect() {
        obstacleEffect = null;
    }

    private boolean tankHasObstacleEffect() {
        return obstacleEffect != null;
    }

    private void setTankPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    private Direction getMovingDirection(int x, int y, int newX, int newY) {
        if (newX - x > 0) return Direction.RIGHT;
        else if (newX - x < 0) return Direction.LEFT;
        else if (newY - y < 0) return Direction.DOWN;
        return Direction.UP;
    }

    @Override
    public void act(int... p) {
        if (ammunition.enoughAmmo() && gun.tryToFire()) {
            Bullet bullet = new Bullet(field, direction, copy(), this,
                    bullet1 -> Tank.this.bullets.remove(bullet1));

            ammunition.ammoAfterShotDecrement();

            if (!bullets.contains(bullet)) {
                bullets.add(bullet);
            }
        }
    }

    @Override
    public void message(String command) {
        // do nothing
    }

    public Iterable<Bullet> getBullets() {
        return new LinkedList<>(bullets);
    }

    public void setField(Field field) {
        this.field = field;
        int xx = x;
        int yy = y;
        while (field.isBarrier(xx, yy)) {
            xx = dice.next(field.size());
            yy = dice.next(field.size());
        }
        setTankPosition(xx, yy);
        alive = true;
    }

    public void kill(Bullet bullet) {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void removeBullets() {
        bullets.clear();
    }

    @Override
    public void tick() {
        gun.tick();
        resetPreviousDirection();
        if (tankHasObstacleEffect()) {
            obstacleEffect.tick();
        }
    }

    private void resetPreviousDirection() {
        previousDirection = null;
    }


    public Ammunition getAmmunition() {
        return ammunition;
    }

    public Health getHealth() {
        return health;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        if (isAlive()) {
            if (player.getTank() == this) {
                if (getHealth().getHealthCount() == 2) {
                    switch (direction) {
                        case LEFT:
                            return Elements.TANK_LEFT;
                        case RIGHT:
                            return Elements.TANK_RIGHT;
                        case UP:
                            return Elements.TANK_UP;
                        case DOWN:
                            return Elements.TANK_DOWN;
                        default:
                            throw new RuntimeException("Неправильное состояние танка!");
                    }
                } else {

                    switch (direction) {
                        case LEFT:
                            return Elements.HALF_TANK_LEFT;
                        case RIGHT:
                            return Elements.HALF_TANK_RIGHT;
                        case UP:
                            return Elements.HALF_TANK_UP;
                        case DOWN:
                            return Elements.HALF_TANK_DOWN;
                        default:
                            throw new RuntimeException("Неправильное состояние танка!");
                    }
                }
            } else {
                switch (direction) {
                    case LEFT:
                        return Elements.OTHER_TANK_LEFT;
                    case RIGHT:
                        return Elements.OTHER_TANK_RIGHT;
                    case UP:
                        return Elements.OTHER_TANK_UP;
                    case DOWN:
                        return Elements.OTHER_TANK_DOWN;
                    default:
                        throw new RuntimeException("Неправильное состояние танка!");
                }
            }
        } else {
            return Elements.BANG;
        }
    }

    public void refreshState() {
        ammunition.refreshAmmo();
        health.refreshHealth();
    }

    public enum Type {
        Player, AI
    }

    private boolean isItTurn(Direction nextDirection) {
        if (previousDirection != null) {
            if (nextDirection == Direction.DOWN && previousDirection == Direction.UP) {
                return true;
            } else if (nextDirection == Direction.UP && previousDirection == Direction.DOWN) {
                return true;
            } else if (nextDirection == Direction.LEFT && previousDirection == Direction.RIGHT) {
                return true;
            } else if (nextDirection == Direction.RIGHT && previousDirection == Direction.LEFT) {
                return true;
            }
        }
        return false;
    }

    private boolean isTankMove() {
        if (isItTurn(direction)) {
            return false;
        } else {
            previousDirection = direction;
            return true;
        }
    }
}
