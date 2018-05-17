/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.mygdx.game.model.spaceships.PlayerSpaceship;
import com.mygdx.game.model.bullets.Bullet;
import com.mygdx.game.model.spaceships.SpaceShip;
import com.mygdx.game.model.spaceships.SpaceShipFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.buffs.*;
import com.mygdx.game.model.spaceships.BossShip;
import com.mygdx.game.view.SpriteRenderer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Класс модели игры
 *
 * @author Admin
 */
public class LevelModel implements ILevelModel {
    /// Скорость игры
    protected float gameSpeed;         
    public float gameSpeed() {
        return gameSpeed;
    }
    public void setGameSpeed(float speed) {
        if (speed < 0) {
            speed = 30f;
        }
        gameSpeed = speed;
    }
    
    /// Длительность игры
    public float gameDuration;      
    public float gameDuration() {
        return gameDuration;
    }    
    public void setGameDuration(float duration){
        gameDuration = duration;
    }
    
    /// Текущее игровое время
    public float currentTime;       
    public float currentTime() {
        return currentTime;
    }
    

    public final int height;        /// Высота игрового поля
    public float height() {return height;}
    public final int width;         /// Ширина игрового поля
    public float width() {return width;}
    
    
    /**
     * Статус игры
     */
    private GameState gameStatus = GameState.INGAME;

    @Override
    public GameState gameState() {
        return gameStatus;
    }

    /**
     * Проверка текущего статуса
     */
    private void checkGameState() {
        if (player.disposed()) {
            gameStatus = GameState.GAMEOVER;
            return;
        }

        if (gameStatus == GameState.INGAME && currentTime > gameDuration) {
            gameStatus = GameState.BOSSFIGHT;

            boss.renderer().isActive = true;

            boss.transform().X = width;
            boss.transform().Y = height / 2;

            enemies.add(boss);
        }

        if (gameStatus == GameState.BOSSFIGHT && enemies.isEmpty()) {
            gameStatus = GameState.WIN;
        }
    }

    /**
     * ********************** Объекты игрового поля
     * ***********************************
     */
    private LinkedList<SpaceShip> enemies = new LinkedList<SpaceShip>();        /// Контейнер с врагами
    private LinkedList<Bullet> playerBullets = new LinkedList<Bullet>();        /// Контейнер пуль игрока
    private LinkedList<Bullet> enemieBullets = new LinkedList<Bullet>();        /// Контейнер вражеских пуль
    private LinkedList<Buff> buffs = new LinkedList<Buff>();                    /// Контейнер баффов
    private LinkedList<Component> modules = new LinkedList<Component>();        /// Контейнер модулей
    private SpaceShip player;                                                   /// Корабль игрока
    private SpaceShip boss;

    public SpaceShip playerShip() {
        return player;
    }
    public void setPlayerShip(SpaceShip ship) {
        player = ship;
    }
    
    public List<SpaceShip> enemies() {
        return enemies;
    }
    public void addEnemie(SpaceShip ship) {
        enemies.add(ship);
    }
    
    public void addBullet(Bullet bull){
        if (bull.sender() == player) {
            playerBullets.add(bull);
        } else {
            enemieBullets.add(bull);
        }
    }
    public List<Bullet> playerBullets(){
        return playerBullets;
    }
    public List<Bullet> enemieBullets(){
        return enemieBullets;
    }
    
    public List<Component> modules(){
        return modules;
    }    
    public void addModule(Component module){
        modules.add(module);
        //module.load();
    }
    
    
    
    
    /**
     * Конструктор
     */
    public LevelModel() {
        gameSpeed = 30f;
        gameDuration = 60f;
        currentTime = 0f;

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        // генерация игрока
        player = new PlayerSpaceship(new Transform(Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() / 2), 3, true, 0.2f, 190f, 400f);

        // генерация босса
        boss = new BossShip(false, 1.5f, 30, 250, player);
        boss.renderer().isActive = false;
    }


    public void updateModules(float delta) {
        for (Component module : modules) {
            module.update(delta);
        }
    }

    /**
     * Проверка позиции пуль игрока и вражеских кораблей
     *
     * @param bul
     */
    private void checkPlayerBulletCollisions(Bullet bul) {
        ListIterator<SpaceShip> iter = enemies.listIterator();

        while (iter.hasNext()) {
            SpaceShip enm = iter.next();

            if (enm.transform().isCollision(bul.transform())) {
                enm.hit(bul.damage());
                bul.dispose();
            }
        }
    }

    /**
     * Проверка позиции игрока и вражеских пуль
     *
     * @param bul
     */
    private void checkEnemieBulletCollisions(Bullet bul) {
        if (player.transform().isCollision(bul.transform())) {
            player.hit(bul.damage());
            bul.dispose();
        }
    }

    /**
     * Обновление пуль игрока
     *
     * @param delta
     */
    private void updatePlayerBullets(float delta) {
        ListIterator<Bullet> iter = playerBullets.listIterator();

        while (iter.hasNext()) {
            Bullet bul = iter.next();

            // проверить столкновение с врагами
            checkPlayerBulletCollisions(bul);

            if (isOutOfWindow(bul) || bul.disposed()) {
                iter.remove();
                continue;
            }

            bul.update(delta);
        }
    }

    /**
     * Обновление пуль врагов
     *
     * @param delta
     */
    private void updateEnemieBullets(float delta) {
        ListIterator<Bullet> iter = enemieBullets.listIterator();

        while (iter.hasNext()) {
            Bullet bul = iter.next();

            //проверить столкновение с игроком
            checkEnemieBulletCollisions(bul);

            if (isOutOfWindow(bul) || bul.disposed()) {
                iter.remove();
                continue;
            }

            bul.update(delta);
        }
    }

    /**
     * Обновление бафов
     *
     * @param delta
     */
    private void updateBuffs(float delta) {

        // генерируем новые
        generateBuffs(delta);

        ListIterator<Buff> iter = buffs.listIterator();

        while (iter.hasNext()) {
            Buff buf = iter.next();

            if (isOutOfWindow(buf) || buf.disposed()) {
                iter.remove();
                continue;
            }

            buf.update(delta);
        }
    }

    /**
     * Обновление врагов
     *
     * @param delta
     */
    private void updateEnemies(float delta) {
        ListIterator<SpaceShip> iter = enemies.listIterator();

        while (iter.hasNext()) {
            SpaceShip ship = iter.next();

            if (isOutOfWindow(ship) || ship.disposed()) {
                iter.remove();
                continue;
            }

            ship.update(delta);
        }
    }

    /**
     * Обновление
     *
     * @param delta
     */
    public void update(float delta) {
        currentTime += delta;

        //проверяем состояние игры
        checkGameState();

        //генерируем врагов
        generateEnemies(delta);

        // обновляем игрока и врагов
        updateEnemies(delta);
        player.update(delta);

        //обновляем пули
        updateEnemieBullets(delta);
        updatePlayerBullets(delta);

        //Обновляем баффы
        updateBuffs(delta);

        //Обновляем модули
        updateModules(delta);
    }

    /**
     * Обновление отрисовки
     *
     * @param delta
     * @param batch
     */
    public void updateRenderer(float delta, SpriteBatch batch) {
        // отрисовать врагов
        ListIterator<SpaceShip> iter = enemies.listIterator();
        while (iter.hasNext()) {
            iter.next().renderer().render(delta, batch);
        }

        // отрисоватьб пули врагов
        ListIterator<Bullet> iter1 = enemieBullets.listIterator();
        while (iter1.hasNext()) {
            iter1.next().renderer().render(delta, batch);
        }

        //отрисовать баффы
        ListIterator<Buff> iter2 = buffs.listIterator();
        while (iter2.hasNext()) {
            iter2.next().renderer().render(delta, batch);
        }

        //отрисовать пули игрока
        iter1 = playerBullets.listIterator();
        while (iter1.hasNext()) {
            iter1.next().renderer().render(delta, batch);
        }

        // отрисовать игрока
        player.renderer().render(delta, batch);
    }

    /**
     * ************************************Генерация
     * врагов********************************************
     */
    private float enemieSpawnTimeout = 1f;                          /// Время между спаунами врагов
    private float enemieLastSpawnTime = 0f;                         /// Время последнего спауна
    private float minSpawnY = Gdx.graphics.getHeight() * 0.05f;     /// Минимальный Y спауна
    private float maxSpawnY = Gdx.graphics.getHeight() * 0.95f;     /// Максимальный Y спауна
    private float spawnX = Gdx.graphics.getWidth() * 1.07f;         /// X спауна
    private final Random random = new Random();                     /// Рандомайзер

    /**
     * Генератор врагов
     *
     * @param delta
     */
    private void generateEnemies(float delta) {
        if (gameStatus != GameState.INGAME) {
            return;
        }

        enemieLastSpawnTime += delta;

        if (enemieLastSpawnTime < enemieSpawnTimeout) {
            return;
        }

        // сгенерировать вражеский корабль
        SpaceShip ship = generateRandomShip();

        // задаем начальную позицию
        ship.transform().X = spawnX;
        ship.transform().Y = random.nextInt((int) (maxSpawnY - minSpawnY)) + minSpawnY;

        //добавляем в активные объекты
        enemies.add(ship);

        enemieLastSpawnTime = 0f;
    }

    /**
     * Генератор космических пораблей
     *
     * @return
     */
    private SpaceShip generateRandomShip() {
        int val = random.nextInt(3);

        switch (val) {
            case 0:
                return SpaceShipFactory.getSpaceShip("StraightShootingSpaceship", false, 1, 1f, 100f, 250f, null);
            case 1:
                return SpaceShipFactory.getSpaceShip("BigSpaceShip", false, 1, 1f, 50f, 250f, null);
            case 2:
                return SpaceShipFactory.getSpaceShip("ArcShootingSpaceShip", false, 1, 1.5f, 50f, 250f, player);
        }

        return null;
    }

    /**
     * *******************************************Генерация
     * бафов**********************************************************
     */
    private float buffSpawnTimeout = 20f;            /// Время между спаунами бафов 
    private float buffLastSpawnTime = 20f;           /// Время последнего спауна 

    /**
     * Генератор баффов
     *
     * @param delta
     */
    private void generateBuffs(float delta) {
        buffLastSpawnTime += delta;

        if (buffLastSpawnTime < buffSpawnTimeout) {
            return;
        }

        // сгенерировать вражеский корабль
        Buff buf = generateRandomBuff();

        // задаем начальную позицию
        buf.transform().X = spawnX;
        buf.transform().Y = random.nextInt((int) (maxSpawnY - minSpawnY)) + minSpawnY;

        //добавляем в активные объекты
        buffs.add(buf);

        buffLastSpawnTime = 0f;
    }

    /**
     * Генератор случайных баффов
     *
     * @return
     */
    private Buff generateRandomBuff() {
        int val = random.nextInt(3);

        switch (val) {
            case 0:

            case 1:
                return BuffFactory.getBuff("LifeIncrease", player);
            case 2:
                return BuffFactory.getBuff("FireRateIncrease", player);
            case 3:
                return BuffFactory.getBuff("UnlimitedHealth", player);
        }

        return null;
    }

    /**
     * Проверка выхода объекта за границы экрана
     *
     * @param o
     * @return
     */
    public boolean isOutOfWindow(GameObject o) {
        Transform t = o.transform();
        return t.X < -width / 4 || t.X > width * 1.5
                || t.Y < -height / 3 || t.Y > height * 1.5;
    }

}
