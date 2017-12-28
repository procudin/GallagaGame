/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.bullets;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.spaceships.SpaceShip;

/**
 * Класс пули
 * @author Admin
 */
public abstract class Bullet extends GameObject{
    /**
     * Конструктор
     * @param sender 
     */
    protected Bullet(GameObject sender){
        super();       
        this.sender = sender;
        _damage = 100f;
    }
    
    /**
     * Конструктор
     * @param damage
     * @param sender 
     */
    protected Bullet(float damage,GameObject sender){
        super();       
        this.sender = sender;
        _damage = damage;
    }    
    
    /**
     * Урон
     */
    private float _damage;
    public float damage(){return _damage;}
    public void setDamage(float damage) {_damage = damage; }
    
    private GameObject sender;              /// Отправитель
    public GameObject sender() { return sender;}
}
