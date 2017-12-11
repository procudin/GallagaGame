/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.spaceships;

import com.mygdx.game.model.GameObject;

/**
 *
 * @author Admin
 */
public abstract class SpaceShip extends GameObject{        
    public SpaceShip(float health,int lifes){
        super();  
        
        _lifes = lifes;
        _health = health;   
        _maxHealth = health;
    }
    
    public SpaceShip(float health){
        this(health,1);  
    }
    
    
    public void hit(float damage){
        _health-=damage;
        
        if (_health<0){
            _lifes--;
            _health = _maxHealth;
            if (_lifes<=0)
                this.dispose();
        }
    }
    
    private float _maxHealth; public float maxHealth(){return _maxHealth;} public void setMaxHealth(float health){_maxHealth = health;}
    private float _health;    public float health(){ return _health;}  
    private int _lifes;       public int lifes(){ return _lifes;}     public void setLifes(int lifes){ _lifes = lifes; }
}
