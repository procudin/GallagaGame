/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.bullets;

import com.mygdx.game.model.GameObject;
import com.mygdx.game.model.spaceships.SpaceShip;

/**
 *
 * @author Admin
 */
public abstract class Bullet extends GameObject{
    protected Bullet(GameObject sender){
        super();       
        this.sender = sender;
        _damage = 100f;
    }
    
    protected Bullet(float damage,GameObject sender){
        super();       
        this.sender = sender;
        _damage = damage;
    }    
    
    private float _damage;
    public float damage(){return _damage;}
    public void setDamage(float damage) {_damage = damage; }
    
    private GameObject sender;
    public GameObject sender() { return sender;}
}
