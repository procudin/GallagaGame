/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model;

/**
 *
 * @author Admin
 */
public abstract class Bullet extends GameObject{
    protected Bullet(){
        super();       
        
        _damage = 100f;
    }
    
    protected Bullet(float damage){
        super();       
        
        _damage = damage;
    }    
    
    private float _damage;
    public float damage(){return _damage;}
    public void setDamage(float damage) {_damage = damage; }
    
}
