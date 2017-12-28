/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.spaceships;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.Gun;
import java.util.ArrayList;

/**
 * Класс космического корабля
 * @author Admin
 */
public abstract class SpaceShip extends GameObject{        
    /**
     * Конструктор
     * @param health
     * @param lifes 
     */
    public SpaceShip(float health,int lifes){
        super();  
        
        _lifes = lifes;
        _health = health;   
        _maxHealth = health;
    }
    
    /**
     * Конструктор
     * @param health 
     */
    public SpaceShip(float health){
        this(health,1);  
    }
    
    /**
     * Нанести урон
     * @param damage 
     */
    public void hit(float damage){
        _health-=damage;
        
        if (_health<0){
            _lifes--;
            _health = _maxHealth;
            if (_lifes<=0)
                this.dispose();
        }
    }
    
    /**
     * Обновить
     * @param delta 
     */
    @Override
    public void update(float delta){
        super.update(delta);
        
        for(Gun g : _guns){
            g.update(delta);
        }
    }
    
    /**
     * Пушки
     */
    private ArrayList<Gun> _guns = new ArrayList<Gun>();
    public void addGun(Gun gun){ _guns.add(gun);  }
    public ArrayList<Gun> guns(){ return _guns;}
    public void removeGun(int index){ if (index>=0 && index<_guns.size()) _guns.remove(index); }
    
    /**
     * Максимальное здоровье
     */
    private float _maxHealth; public float maxHealth(){return _maxHealth;} public void setMaxHealth(float health){_maxHealth = health;}
    /**
     * Текущее здоровье
     */
    private float _health;    public float health(){ return _health;}  public void setHealth(float health) {_health= health;}
    /**
     * Количество жизней 
     */
    private int _lifes;       public int lifes(){ return _lifes;}     public void setLifes(int lifes){ _lifes = lifes; }
}
