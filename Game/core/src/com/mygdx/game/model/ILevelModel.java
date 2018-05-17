/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.bullets.Bullet;
import com.mygdx.game.model.spaceships.SpaceShip;
import java.util.List;





/**
 * Интерфейс модели уровня
 * @author Archi
 */
public interface ILevelModel {
    /**
     * Скорость игры
     * @return 
     */
    public float gameSpeed();
    /**
     * Задать скорость игры
     * @return 
     */
    public void setGameSpeed(float speed);
    
    
    /**
     * Длительность игры
     * @return 
     */
    public float gameDuration();
    /**
     * Задать длительность игры
     * @param duration 
     */
    public void setGameDuration(float duration);
    
    
    /**
     * Время с момента начали игры
     * @return 
     */
    public float currentTime();
    
    
    /**
     * Состояние игры
     * @return 
     */
    public GameState gameState();
    
    /**
     * Ширина игры
     * @return 
     */
    public float width();
    /**
     * Высота игры
     * @return 
     */
    public float height();
    
    
    /**
     * Получить корабль игрока
     * @return 
     */
    public SpaceShip playerShip();    
    /**
     * Задать корабль игрока
     * @param ship 
     */
    public void setPlayerShip(SpaceShip ship);
        
        
    
    /**
     * Получить множетво врагов
     * @return 
     */
    public List<SpaceShip> enemies();
    /**
     * Добавить врага
     * @param ship 
     */
    public void addEnemie(SpaceShip ship);
    
    
    
    
    
    /**
     * Добавить пулю
     * @param bull 
     */
    public void addBullet(Bullet bull);
    /**
     * Получить множество пуль игрока
     * @return 
     */    
    public List<Bullet> playerBullets();    
    /**
     * Получить множество путь врагов
     * @return 
     */
    public List<Bullet> enemieBullets();
    
    
    
    
    /**
     * Получить множество модулей
     * @return 
     */    
    public List<Component> modules();
    /**
     * Добавить модуль
     * @return 
     */    
    public void addModule(Component module);
    
    
    
    
    
    /**
     * Обновление модели
     * @param delta 
     */
    public void update(float delta);
    /**
     * Обновление отрисовки
     * @param delta
     * @param batch 
     */
    public void updateRenderer(float delta, SpriteBatch batch);
}
